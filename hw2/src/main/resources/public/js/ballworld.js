'use strict'

//app to draw polymorphic shapes on canvas
var app;

/**
 * Creates a an object that has functions that draw and clear objects in the BallWorld
 * @param canvas  The canvas to draw balls on
 * @returns {{drawBall: drawBall, clear: clear}}
 */
function createApp(canvas) {
    var c = canvas.getContext("2d");

    var drawBall = function(x,radius,color) {
        c.fillStyle=color;
        c.beginPath();
        c.arc(x.x,x.y,radius,0,Math.PI*2,true);
        c.closePath();
        c.fill();
    }


    var clear = function() {
        c.clearRect(0,0, 500, 500);
    }


    return {
        drawBall: drawBall,

        clear: clear
    }
}
//setInterval(updateLine, "200");
/**
 * When the window loads, get the app that can draw and clear balls on the canvas
 */
window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    //set interval for updating
    intervalID=setInterval(updateBallWorld, "100");
    //7 balls and clear
    $("#btn-straight").click({kind:"straight"},createBall);
    $("#btn-rotation").click({kind:"rotation"},createBall);
    $("#btn-changesize").click({kind:"changing_size"},createBall);
    $("#btn-changecolor").click({kind:"changing_color"},createBall);
    $("#btn-random").click({kind:"random"},createBall);
    $("#btn-speed").click({kind:"speed"},createBall);
    $("#btn-low").click({kind:"low"},createBall);

    $("#btn-clear").click(clearall);

}
var intervalID;
/**
 * Create a ball at a location on the canvas
 */
function createBall(event) {
    var kind=event.data.kind;
    //console.log("send")
    //send a request to the controller with parameter "kind"
    $.get("/ball/"+kind, function (data, status) {
        var obj = JSON.parse(data);
        app.drawBall(obj.l,obj.r,obj.c);

    }, "json");


}



/**
 * Update all the balls in the BallWorld
 */
function updateBallWorld() {
    clear();
    $.get("/update", function(data, status) {
        console.log(typeof(data));
        var obj = JSON.parse(data);
        var obs = obj.obs;
        console.log(obs[0])
        for(var j = 0; j < obs.length; j++) {
            console.log(obs[j].l);
            app.drawBall(obs[j].l,obs[j].r,obs[j].c);}


    }, "json");
}



/**
 * Clear the canvas
 */
function clear() {


    app.clear();

}
/**
 * Clear the memory
 */

function clearall(){
    $.get("/clear", function(data, status) {
    }, "json");


}
