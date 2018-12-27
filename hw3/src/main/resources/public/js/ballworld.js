'use strict'

//app to draw polymorphic shapes on canvas
var app;

/**
 * Application returns object that draws on or clears the canvas
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
        c.clearRect(0, 0, canvas.width, canvas.height);
    }


    return {
        drawBall: drawBall,
        clear: clear
    }
}

/**
 * Setup button event listeners and update BallWorld interval
 */
window.onload = function() {
    clear();
    app = createApp(document.querySelector("canvas"));

    intervalID=setInterval(updateBallWorld, "100");
    canvasDims();

    $("#btn-noramlball").click(loadBall);
    $("#btn-strategyball").click(loadSwitcher);
    $("#btn-switch").click(switchStrategy);
    $("#btn-clear").click(clear);
}
var send;
var intervalID;
/**
 * load a ball at a location on the canvas
 */





function loadBall() {

    var values = $('#strategy_select').val();
    console.log(values.length===1);
    if (values.length===1){
        $.post("/load", {normal:values[0] }, function (data, status) {
            var obj = JSON.parse(data);
            app.drawBall(obj.loc,obj.radius,obj.color);
        }, "json");}
    else{
        $.post("/load", {normal:values.join() }, function (data, status) {
            var obj = JSON.parse(data);
            app.drawBall(obj.loc,obj.radius,obj.color);
        }, "json");}
}

/**
 * load a switcher ball at a location on the canvas
 */
function loadSwitcher() {
    var values = $('#strategy_select').val();

    if (values.length==1){
        $.post("/load/switcher", {switcher:values[0] }, function (data, status) {
            var obj = JSON.parse(data);
            app.drawBall(obj.loc,obj.radius,obj.color);
        }, "json");}
    else{
        $.post("/load/switcher", {switcher:values.join() }, function (data, status) {
            var obj = JSON.parse(data);
            app.drawBall(obj.loc,obj.radius,obj.color);
        }, "json");}








}

/**
 * Switch strategies for all the switcher strategy balls
 */
function switchStrategy() {

    var values = $('#strategy_select').val();
    console.log(values);
    if (values.length==1){
        $.post("/switch", {switcher:values[0] }, function (data, status) {
        }, "json");
    }
    else{
        console.log(values.join());
        $.post("/switch", {switcher:values.join("") }, function (data, status) {
        }, "json");


    }

}

/**
 * Update all the balls in the BallWorld
 */
function updateBallWorld() {
    app.clear();
    $.get("/update", function(data, status) {

        var obj = JSON.parse(data);
        var obs = obj.obs;
        //console.log(obs[0])
        for(var j = 0; j < obs.length; j++) {
            console.log(obs[j].loc);
            app.drawBall(obs[j].loc,obs[j].radius,obs[j].color);}
    }, "json");
}

/**
 * Dimensions of Canvas
 */

function canvasDims(){

    var getsize = document.getElementById("mycanvas");
    console.log(getsize.width,getsize.height)

    $.get("/canvas/"+getsize.width+"/"+getsize.height, function(data, status) {
    }, "json");



}

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear", function(data, status) {
    }, "json");
}
