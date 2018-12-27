'use strict';

//const webSocket = new WebSocket("wss://" + location.hostname + ":" + location.port + "/chatapp");
const webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chatapp");
var userId;
var myUserId = -1;
var myRoomId = -1;
var intervalId;
var intervalCnt = 0;

/**
 * Entry point into chat room
 */
window.onload = function() {

    myUserId = -1;
    myRoomId = -1;
    $('#loginModal').modal({backdrop: 'static', keyboard: false, show: true});

    webSocket.onclose = () => {
        $('#timeoutModal').modal({backdrop: 'static', keyboard: false, show: true});
    }

    var signup = function () {
        signupMessage()
    }
    $("#signup").click(signup);

    var leaveAll = function () {
        leaveChatroom(-1)
    }
    $("#btn-leaveAll").click(leaveAll);

    var send = function(){
        sendMessage($("#newMsgText").val())
    }
    $("#btn-sendMsg").click(send);

    var sendAll = function() {
        sendMessageToAll($("#PMAllText").val())
        $("#PMAllModal").modal('hide');
    }
    $("#btn-msgAll").click(sendAll);


    webSocket.onmessage = (msg) => {
        updateChatRoom(msg);
    }
};

/**
 * send message to sign up
 */
function signupMessage() {
    var age = parseInt($("#ageInput").val());
    var loc = $("#locInput").val();
    var school = $("#schoolInput").val();
    var chatroomAgeMin = parseInt($("#chatroomAgeInputMin").val());
    var chatroomAgeMax = parseInt($("#chatroomAgeInputMax").val());
    var chatroomLoc = [];
    var select1 = document.getElementById("chatroomLocInput");
    var opts1 = select1 && select1.options;
    for (var i = 0; i < opts1.length; i++) {
        if (opts1[i].selected == true) {
            chatroomLoc.push(opts1[i].value);
        }
    }
    var chatroomSchool = [];
    var select2 = document.getElementById("chatroomSchoolInput");
    var opts2 = select2 && select2.options;
    for (var i = 0; i < opts2.length; i++) {
        if (opts2[i].selected == true) {
            chatroomSchool.push(opts2[i].value);
        }
    }
    // validate profile and chat room restriction
    if (age <= 0 || age > 200 || isNaN(age)) {
        // invalid age
        document.getElementById("ageInput").className = "form-control is-invalid";
    } else {
        // valid age
        document.getElementById("ageInput").className = "form-control";

        if ( (chatroomAgeMin && chatroomAgeMin > age) || (chatroomAgeMax && chatroomAgeMax < age)) {
            // ageMax doesn't qualify
            document.getElementById("chatroomAgeInputMin").className = "form-control is-invalid";
            document.getElementById("chatroomAgeInputMax").className = "form-control is-invalid";
        } else {
            // qualify or blank
            document.getElementById("chatroomAgeInputMin").className = "form-control";
            document.getElementById("chatroomAgeInputMax").className = "form-control";

            if (!checkLocSchool(loc, chatroomLoc)) {
                // loc doesn't qualify
                document.getElementById("chatroomLocInput").className = "form-control is-invalid";
            } else {
                // qualify or blank
                document.getElementById("chatroomLocInput").className = "form-control";

                if (!checkLocSchool(school, chatroomSchool)) {
                    // school doesn't qualify
                    document.getElementById("chatroomSchoolInput").className = "form-control is-invalid";
                } else {
                    document.getElementById("chatroomSchoolInput").className = "form-control";

                    document.getElementById("profileAge").innerText = age;
                    document.getElementById("profileLoc").innerText = loc;
                    document.getElementById("profileSchool").innerText = school;
                    if (chatroomAgeMin) {
                        document.getElementById("chatroomAgeMin").innerText = chatroomAgeMin;
                    } else {
                        document.getElementById("chatroomAgeMin").innerText = "0";
                        chatroomAgeMin = 0;
                    }
                    if (chatroomAgeMax) {
                        document.getElementById("chatroomAgeMax").innerText = chatroomAgeMax;
                    } else {
                        document.getElementById("chatroomAgeMax").innerText = "200";
                        chatroomAgeMax = 200;
                    }
                    document.getElementById("chatroomLoc").innerText = chatroomLoc.length ? chatroomLoc : "N/A";
                    document.getElementById("chatroomSchool").innerText = chatroomSchool.length ? chatroomSchool : "N/A";

                    // send message to sign up and dismiss the modal
                    var objContent = {commandType: "signup", age: age, location: loc, school: school, ageResL: chatroomAgeMin, ageResU: chatroomAgeMax, locRes: chatroomLoc, schoolRes: chatroomSchool};
                    var objJSON = JSON.stringify(objContent);
                    webSocket.send(objJSON);
                    $("#loginModal").modal('hide')
                }
            }
        }
    }

}

/**
 * validate location and school in chat room restriction
 * @param profileInfo info from profile
 * @param chatroomInfo info from chat room restriction
 * @returns {boolean} if valid
 */
function checkLocSchool(profileInfo, chatroomInfo) {
    if (chatroomInfo.length == 0) return true;

    for (var i = 0; i < chatroomInfo.length; i++) {
        if (profileInfo == chatroomInfo[i])
            return true;
    }
    return false;
}

/**
 * send message to leave one or all chat rooms
 * @param roomId -1 means leaving all chat rooms
 */
function leaveChatroom(roomId) {
    if (roomId === -1) {
        document.getElementById("chatroomList").innerHTML = "";
        newChatroom(myRoomId, true);
    } else if (roomId === myRoomId) {
        $("#cardChatroom"+roomId).remove();
        newChatroom(roomId, true);
    } else {
        $("#cardChatroom"+roomId).remove();
        var availableList = document.getElementById("joinList");
        addOneAvailableRoom(roomId, availableList);
    }
    var objContent = {commandType: "leaveRoom", chatroom: roomId};
    var objJSON = JSON.stringify(objContent);
    webSocket.send(objJSON);
}

/**
 * join a chat room
 * @param roomId
 */
function joinChatroom(roomId) {
    var objContent = {commandType: "joinRoom", chatroom: roomId};
    var objJSON = JSON.stringify(objContent);
    webSocket.send(objJSON);
    $("#availableRoom"+roomId).remove();
    newChatroom(roomId, false);
}

/**
 * get all the chat histories between me and the user
 * @param user user to chat with
 */
function getChatHistory(user) {
    var objContent = {commandType: "chatHistory", name: user};
    var objJSON = JSON.stringify(objContent);
    userId = user;
    webSocket.send(objJSON);
}

/**
 * Send a message to the server.
 * @param msg  The message to send to the server.
 */
function sendMessage(msg) {
    if (msg !== "") {
        var objContent = {commandType: "message", name: userId, content: msg};
        var objJSON = JSON.stringify(objContent);
        webSocket.send(objJSON);
        $("#newMsgText").val("")
    }
}

/**
 * send a private message to all the members in the chatroom
 * @param msg
 */
function sendMessageToAll(msg) {
    if (msg !== "") {
        var objContent = {commandType: "message", name: -1, content: msg};
        var objJSON = JSON.stringify(objContent);
        webSocket.send(objJSON);
        $("#PMAllText").val("");
    }
}

/**
 * confirm that a message is received
 * @param msgId
 */
function ackMessage(msgId) {
    var objContent = {commandType: "ack", msgId: msgId};
    var objJSON = JSON.stringify(objContent);
    webSocket.send(objJSON);
}

/**
 * Update the chat room with a message.
 * @param message  The message to update the chat room with.
 */
function updateChatRoom(message) {
    var data = JSON.parse(message.data)
    switch (data.type) {
        case "RoomNotificationResponse":
            roomNotification(data.roomId, data.notifications);
            break;
        case "RoomUsersResponse":
            roomUsers(data.roomId, data.users);
            break;
        case "UserChatHistoryResponse":
            loadChatHistory(data.chatHistory);
            break;
        case "UserRoomResponse":
            if (myUserId === -1) {
                myUserId = data.userId;
                myRoomId = data.joinedRoomIds[0];
                document.getElementById("profileName").innerText = "User " + myUserId;
                newChatroom(data.joinedRoomIds[0], true);
            }
            if (data.joinedRoomIds.length === 0) {
                getBanned();
                break;
            }
            availableRooms(data.availableRoomIds);
            break;
        case "AckResponse":
            messageACked(data.message);
            break;
        case "NewMessageResponse":
            receiveMessage(data.message);
            break;
    }
    clearInterval(intervalId);
    intervalCnt = 0;
    intervalId = setInterval(maintainLife, 30000);
}

/**
 * user is banned, modal pops up
 */
function getBanned() {
    $('#bannedModal').modal({backdrop: 'static', keyboard: false, show: true});
}

/**
 * a message is received
 * @param message
 */
function messageACked(message) {
    if (message.receiverId === userId) {
        var chatArea = document.getElementById("chatArea");
        var messageBody = document.createElement("div");
        messageBody.className = "row message-body";
        var messageSent = document.createElement("div");
        messageSent.className = "col-lg-12 message-main-sent";
        var sent = document.createElement("div");
        sent.setAttribute("id", "ack")
        sent.className = "sent";
        var messageText = document.createElement("div");
        messageText.className = "message-text";
        var messageTextNode = document.createTextNode(message.message);
        messageText.appendChild(messageTextNode);
        var ackSpan = document.createElement("span");
        ackSpan.className = "message-ack";
        var ackSpanNode = document.createTextNode("received");
        ackSpan.appendChild(ackSpanNode);
        sent.appendChild(messageText);
        messageSent.appendChild(sent);
        messageSent.appendChild(ackSpan);
        messageBody.appendChild(messageSent);
        chatArea.appendChild(messageBody);
        scrollStayBottom(document.getElementById("chatArea"));
    }
}

/**
 * receive a new message
 * @param message
 */
function receiveMessage(message) {
    ackMessage(message.id);

    if (message.senderId === userId) {
        var chatArea = document.getElementById("chatArea");
        var messageBody = document.createElement("div");
        messageBody.className = "row message-body";
        var messageReceived = document.createElement("div");
        messageReceived.className = "col-lg-12 message-main-received";
        var received = document.createElement("div");
        received.className = "received";
        var messageText = document.createElement("div");
        messageText.className = "message-text";
        var messageTextNode = document.createTextNode(message.message);
        messageText.appendChild(messageTextNode);
        received.appendChild(messageText);
        messageReceived.appendChild(received);
        messageBody.appendChild(messageReceived);
        chatArea.appendChild(messageBody);
        scrollStayBottom(document.getElementById("chatArea"));
    } else {
        var newMsgN = document.getElementsByName("newMsg"+message.senderId);
        newMsgN.forEach(newMessageNotification)
        newMsgN.className = "newMsgIcon";
    }
}

/**
 * notify user about the new message
 * @param ele
 */
function newMessageNotification(ele) {
    ele.className = "newMsgIcon";
}

/**
 * dismiss the new message notification
 * @param ele
 */
function nonNewMessageNotification(ele) {
    ele.className = "nonNewMsgIcon";
}

/**
 * render the chat history
 * @param history between two users
 */
function loadChatHistory(history) {
    var chatArea = document.getElementById("chatArea");
    history.forEach(function (record) {
        var messageBody = document.createElement("div");
        messageBody.className = "row message-body";
        if (record.senderId !== myUserId) {
            var messageReceived = document.createElement("div");
            messageReceived.className = "col-lg-12 message-main-received";
            var received = document.createElement("div");
            received.className = "received";
            var messageText = document.createElement("div");
            messageText.className = "message-text";
            var messageTextNode = document.createTextNode(record.message);
            messageText.appendChild(messageTextNode);
            received.appendChild(messageText);
            messageReceived.appendChild(received);
            messageBody.appendChild(messageReceived);
        } else {
            var messageSent = document.createElement("div");
            messageSent.className = "col-lg-12 message-main-sent";
            var sent = document.createElement("div");
            sent.className = "sent";
            var messageText = document.createElement("div");
            messageText.className = "message-text";
            var messageTextNode = document.createTextNode(record.message);
            messageText.appendChild(messageTextNode);
            var ackSpan = document.createElement("span");
            ackSpan.className = "message-ack";
            var ackSpanNode = document.createTextNode("received");
            ackSpan.appendChild(ackSpanNode);
            sent.appendChild(messageText);
            messageSent.appendChild(sent);
            if (record.isReceived) {
                messageSent.appendChild(ackSpan);
            }
            messageBody.appendChild(messageSent);
        }
        chatArea.appendChild(messageBody);
    });
    scrollStayBottom(document.getElementById("chatArea"));
}

/**
 * render latest members in a chatroom
 * @param roomId
 * @param users in the chatroom
 */
function roomUsers(roomId, users) {
    if (users.length === 0) {
        $("#cardChatroom"+roomId).remove();
    } else if (!users.includes(myUserId)) {
        $("#cardChatroom"+roomId).remove();
        addOneAvailableRoom(roomId, document.getElementById("joinList"));
    } else {
        var members = document.getElementById("members"+roomId);
        members.innerHTML = "";
        users.forEach(function (member) {
            var userLi = document.createElement("li");
            userLi.className = "list-group-item";
            var memberDiv = document.createElement("div");
            memberDiv.className = "d-inline-flex align-items-center";
            var memberDivNode;
            if (member === myUserId) {
                memberDivNode = document.createTextNode("User"+member+"(myself)");
                memberDiv.appendChild(memberDivNode);
                if (myRoomId === roomId) {
                    var sendAllBtn = document.createElement("button");
                    sendAllBtn.className = "btn btn-success btn-sm btn-start-chat";
                    sendAllBtn.setAttribute("type", "button");
                    sendAllBtn.setAttribute("data-toggle", "modal");
                    sendAllBtn.setAttribute("data-target", "#PMAllModal");
                    var sendAllBtnNode = document.createTextNode("PM All");
                    sendAllBtn.appendChild(sendAllBtnNode);
                    userLi.appendChild(memberDiv);
                    userLi.appendChild(sendAllBtn);
                } else {
                    userLi.appendChild(memberDiv);
                }
            } else {
                var span = document.createElement("span");
                span.setAttribute("name", "newMsg"+member)
                span.className = "nonNewMsgIcon";
                memberDiv.appendChild(span);
                memberDivNode = document.createTextNode("User"+member);
                memberDiv.appendChild(memberDivNode);
                var chatBtn = document.createElement("button");
                chatBtn.className = "btn btn-success btn-sm btn-start-chat";
                chatBtn.setAttribute("type", "button");
                chatBtn.addEventListener("click", function () {
                    var chatUser = document.getElementById("chatFriendName");
                    chatUser.innerText = "User "+member;
                    var chatArea = document.getElementById("chatArea");
                    chatArea.innerHTML = "";
                    userId = member;
                    var newMsgN = document.getElementsByName("newMsg"+member);
                    newMsgN.forEach(nonNewMessageNotification)
                    newMsgN.className = "nonNewMsgIcon";
                    getChatHistory(member);
                })
                var chatBtnNode = document.createTextNode("Chat");
                chatBtn.appendChild(chatBtnNode);
                userLi.appendChild(memberDiv);
                userLi.appendChild(chatBtn);
            }
            members.appendChild(userLi);
        })
    }
}

/**
 * nnotification about someone entering/ leaving a room
 * @param roomId
 * @param notifications content
 */
function roomNotification(roomId, notifications) {
    var notificationArea = document.getElementById("notification"+roomId);
    notifications.forEach(function (notification) {
        var p = document.createElement("p");
        var pNode = document.createTextNode(notification);
        p.appendChild(pNode);
        notificationArea.appendChild(p);
    });
    scrollStayBottom(notificationArea);
}

/**
 * render a new chatroom in the list
 * @param roomId
 * @param own
 */
function newChatroom(roomId, own) {
    var card = document.createElement("div");
    card.setAttribute("id", "cardChatroom"+roomId);
    card.className = "card";
    var cardHeader = document.createElement("div");
    cardHeader.className = "card-header";
    cardHeader.setAttribute("role", "tab");
    cardHeader.setAttribute("id", "chatroom"+roomId);
    var h5 = document.createElement("h5");
    h5.className = "mb-0 d-flex justify-content-between align-items-center";
    var chatroomName = document.createElement("a");
    chatroomName.setAttribute("data-toggle", "collapse");
    chatroomName.setAttribute("href", "#collapse"+roomId);
    chatroomName.setAttribute("aria-expanded", "false");
    chatroomName.setAttribute("aria-controls", "collapse"+roomId);
    if (own == true) {
        var chatroomNameNode = document.createTextNode("Chatroom"+roomId+" (Mine)");
    } else {
        var chatroomNameNode = document.createTextNode("Chatroom"+roomId);
    }

    chatroomName.appendChild(chatroomNameNode);
    var btnLeave = document.createElement("button");
    btnLeave.className = "btn btn-primary btn-sm";
    btnLeave.setAttribute("type", "button")
    btnLeave.addEventListener("click", function () { leaveChatroom(roomId); });
    var btnLeaveNode = document.createTextNode("Leave");
    btnLeave.appendChild(btnLeaveNode);

    h5.appendChild(chatroomName);
    h5.appendChild(btnLeave);
    cardHeader.appendChild(h5);
    card.appendChild(cardHeader);

    var collapse = document.createElement("div");
    collapse.className = "collapse";
    collapse.setAttribute("id", "collapse"+roomId)
    collapse.setAttribute("role", "tabpanel");
    collapse.setAttribute("aria-labelledby", "chatroom"+roomId);
    collapse.setAttribute("data-parent", "#chatroomList");
    var listGroup = document.createElement("div");
    listGroup.className = "list-group";
    listGroup.setAttribute("id", "members"+roomId);
    var listGroupItem = document.createElement("div");
    listGroupItem.className = "list-group-item";
    listGroupItem.setAttribute("id", "notificationListItem"+roomId);
    var b = document.createElement("b");
    var bNode = document.createTextNode("Notifications:")
    b.appendChild(bNode);
    var hr = document.createElement("hr");
    var notification = document.createElement("div");
    notification.setAttribute("style", "overflow-y: scroll; height:150px;");
    notification.setAttribute("id", "notification"+roomId);

    listGroupItem.appendChild(b);
    listGroupItem.appendChild(hr);
    listGroupItem.appendChild(notification);
    if (own) {
        var myselfLi = document.createElement("li");
        myselfLi.className = "list-group-item";
        var myselfDiv = document.createElement("div");
        myselfDiv.className = "d-inline-flex align-items-center";
        var myselfDivNode = document.createTextNode("User"+myUserId+"(myself)");
        myselfDiv.appendChild(myselfDivNode);
        var sendAllBtn = document.createElement("button");
        sendAllBtn.className = "btn btn-success btn-sm btn-start-chat";
        sendAllBtn.setAttribute("type", "button");
        sendAllBtn.setAttribute("data-toggle", "modal");
        sendAllBtn.setAttribute("data-target", "#PMAllModal");
        var sendAllBtnNode = document.createTextNode("PM All");
        sendAllBtn.appendChild(sendAllBtnNode);
        myselfLi.appendChild(myselfDiv);
        myselfLi.appendChild(sendAllBtn);
        listGroup.appendChild(myselfLi);
    }
    collapse.appendChild(listGroup);
    collapse.appendChild(listGroupItem);
    card.appendChild(collapse);
    var chatroomList = document.getElementById("chatroomList");
    chatroomList.appendChild(card);
}

/**
 * render all availble chatrooms
 * @param roomIds
 */
function availableRooms(roomIds) {
    var availableList = document.getElementById("joinList");
    availableList.innerHTML = "";
    roomIds.forEach(function(roomId){
        addOneAvailableRoom(roomId, availableList)
    })
}

/**
 * add a certain room to available room list
 * @param roomId
 * @param availableList element
 */
function addOneAvailableRoom(roomId, availableList) {
    var li = document.createElement("li");
    li.setAttribute("id", "availableRoom"+roomId);
    li.className = "list-group-item d-flex justify-content-between align-items-center";
    var a = document.createElement("a");
    var aNode = document.createTextNode("Chatroom "+roomId);
    a.appendChild(aNode);
    var btn = document.createElement("button");
    var btnNode = document.createTextNode("Join");
    btn.setAttribute("value", roomId);
    btn.appendChild(btnNode);
    btn.className = "btn btn-info btn-sm";
    btn.setAttribute("type", "button");
    btn.addEventListener("click", function () {
        joinChatroom(roomId)
    });

    li.appendChild(a);
    li.appendChild(btn);
    availableList.appendChild(li);
}

/**
 * keep scrollable element stay at bottom
 * @param element
 */
function scrollStayBottom(element){
    // var element = document.getElementById("chatArea");
    element.scrollTop = element.scrollHeight;
}

/**
 * send heartbeat message to maintain life cycle
 */
function maintainLife() {
    if (intervalCnt < 10) {
        intervalCnt++;
        var objContent = {commandType: "heartbeat", content: 0};
        var objJSON = JSON.stringify(objContent);
        webSocket.send(objJSON);
    } else {
        intervalCnt = 0;
        clearInterval(intervalId);
    }

}