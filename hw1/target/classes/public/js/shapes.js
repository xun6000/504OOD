'use strict'

//app to draw polymorphic shapes on canvas
var app;


function createApp(canvas) {

    var c = canvas.getContext("2d");

    var drawCircle = function(x, radius,color) {
        //draw the curve by defining the center, radius and angle from 0 to 2pi

        c.fillStyle=color;
        c.beginPath();
        c.arc(x.x,x.y,radius,0,Math.PI*2,true);
        c.closePath();
        c.fill();
    }
    var drawSquare = function(x, size,color) {
        // fill the square by define the location
        c.fillStyle = color;
        c.fillRect(x.x,x.y,size,size);


    }
    var drawTriangle = function(x, size,color) {
        // draw the equilateral triangle by defining each end point
        c.beginPath();
        var height = size*Math.sin(Math.PI/3);
        c.moveTo(x.x,x.y);
        c.lineTo(x.x-0.5*size,x.y-height);
        c.lineTo(x.x+0.5*size,x.y-height);

        c.fillStyle=color;
        c.fill();

    }
    function drawRectangle(x, width, height,color){
        // draw the rectangle by the fillRect function
        if (canvas.getContext){

            c.fillStyle = color;
            c.fillRect(x.x,x.y,width,height);
            }
    }
    function drawSemicircle(x, radius,color){
        // draw the semicircle by defining the angle from 0 to pi
        if (canvas.getContext){

            c.fillStyle = color;
            c.beginPath();
            c.arc(x.x,x.y,radius,0,Math.PI, false);
            c.closePath();
            c.fill();
        }
    }




    var clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);

    }

    return {
        drawCircle: drawCircle,
        drawSquare: drawSquare,
        drawTriangle:drawTriangle,
        drawRectangle:drawRectangle,
        drawSemicircle:drawSemicircle,
        clear: clear
    }
}

window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    $("#btn-circle").click(createCircle);
    $("#btn-square").click(createSquare);
    $("#btn-triangle").click(createTriangle);
    $("#btn-rectangle").click(createRectangle);
    $("#btn-semicircle").click(createSemicircle);


    $("#btn-combo").click(combo);
    $("#btn-clear").click(clear);
}


/**
 * Create a circle at a location on the canvas
 */
function createCircle() {
    $.get("/shape/circle", function (data, status) {
        var obj = JSON.parse(data);
        app.drawCircle(obj.loc,obj.radius,obj.color);
    }, "json");
}
/**
 * Create a square at a location on the canvas
 */
function createSquare() {
    $.get("/shape/square", function (data, status) {
        var obj = JSON.parse(data);
        app.drawSquare(obj.loc,obj.size,obj.color);
    }, "json");
}
/**
 * Create a triangle at a location on the canvas
 */
function createTriangle() {
    $.get("/shape/triangle", function (data, status) {
        var obj = JSON.parse(data);
        app.drawTriangle(obj.loc,obj.size,obj.color);
    }, "json");
}
/**
 * Create a rectangle at a location on the canvas
 */
function createRectangle() {
    $.get("/shape/rectangle", function (data, status) {
        var obj = JSON.parse(data);
        app.drawRectangle(obj.loc,obj.width,obj.height,obj.color);
    }, "json");
}
/**
 * Create a semicircle at a location on the canvas
 */
function createSemicircle() {
    $.get("/shape/semicircle", function (data, status) {
        var obj = JSON.parse(data);
        app.drawSemicircle(obj.loc,obj.radius,obj.color);
    }, "json");
}
/**
 * the combo function will draw all the shapes above on the canvas by calling the function above
 */
function combo() {

    createCircle();
    createSquare();
    createTriangle();
    createSemicircle();
    createRectangle();
    // the code below also works, but it is harder to maintain.

    // $.get("/shape/circle", function (data, status) {
    //     var obj = JSON.parse(data);
    //     app.drawCircle(obj.loc,obj.radius,obj.color);
    // }, "json");
    // $.get("/shape/square", function (data, status) {
    //     var obj = JSON.parse(data);
    //     app.drawSquare(obj.loc,obj.size,obj.color);
    // }, "json");
    // $.get("/shape/triangle", function (data, status) {
    //     var obj = JSON.parse(data);
    //     app.drawTriangle(obj.loc,obj.size,obj.color);
    // }, "json");
    // $.get("/shape/semicircle", function (data, status) {
    //     var obj = JSON.parse(data);
    //     app.drawSemicircle(obj.loc,obj.radius,obj.color);
    // }, "json");
    // $.get("/shape/rectangle", function (data, status) {
    //     var obj = JSON.parse(data);
    //     app.drawRectangle(obj.loc,obj.width,obj.height,obj.color);
    // }, "json");









}

/**
 * Clear the canvas
 */
function clear() {


    app.clear();
}