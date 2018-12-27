'use strict'

//app to draw polymorphic shapes on canvas
var app;
var wallCanvas;
var score;
var locs;
var grid = 10;
var intervalId;

var gameStart = false;

function createApp(canvas) {
    var c = canvas.getContext("2d");

    var drawBean = function(x, y) {
        c.beginPath()
        c.fillStyle = "#f5b99b";
        c.arc(x*grid, y*grid, 2, 0, 2 * Math.PI, false);
        c.closePath()
        c.fill()
    }

    var drawWall = function(x, y, width, length) {
        x *= grid;
        y *= grid;
        width *= grid;
        length *= grid;
        c.beginPath()
        c.fillStyle = "#1428b9"
        c.moveTo(x, y);
        c.lineTo(x, y);
        c.lineTo(x + width, y);
        c.lineTo(x + width, y + length);
        c.lineTo(x, y + length);
        c.lineTo(x, y);

        c.closePath();
        c.fill()
    }

    var drawPower = function(x, y) {
        c.beginPath()
        c.fillStyle = "#f5b99b";
        c.arc(x, y, 4, 0, 2 * Math.PI, false);
        c.closePath()
        c.fill()
    }

    var drawGhost = function(x, y, id) {
        c.save();
        var img = new Image();
        img.src = '../' + id + '.png';
        c.drawImage(img, x*grid, y*grid, grid*2, grid*2);
        c.restore();
    }

    var drawPacman = function (x, y) {
        c.beginPath()
        c.fillStyle = "#fffd55";
        c.arc(x*grid, y*grid, grid, 0, 2 * Math.PI, false);
        c.closePath()
        c.fill()
    }

    var clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    }

    var drawBackground = function() {
        c.fillStyle = "#000000";
        c.fillRect(0,0,canvas.width, canvas.height);
    }

    return {
        drawBean: drawBean,
        drawWall: drawWall,
        drawPower: drawPower,
        drawGhost: drawGhost,
        drawPacman: drawPacman,
        drawBackground: drawBackground,
        clear: clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}


/**
 * Entry point into app
 */
window.onload = function() {
    clearInterval(intervalId);
    app = createApp(document.querySelectorAll("canvas")[0]);
    wallCanvas = createApp(document.querySelectorAll("canvas")[1]);
    wallCanvas.drawBackground();
    canvasDims();
}

/**
 * Change direction of player
 * use keyboard to trigger
 *
 */
function move(direction) {
    $.get("/move/"+direction, function (data, status) {
    }, "json");
}


/**
 *  start/restart a game
 */
function start() {
    intervalId = setInterval(updatePaintObjWorld, 20);
    showBanner(-1);
    $.get("/start", function (data, status) {
    }, "json");
}

/**
 *   update the everything
 */
function updatePaintObjWorld() {

    $.get("/update", function(data, status) {
        score = data.score;
        document.getElementById("score").innerText = score;
        var obs = data.obs;
        locs = [];
        for (var i = 0; i < obs.length; i++) {
            switch (obs[i].type) {
                case "pacman":
                    drawPacman(obs[i].loc.x, obs[i].loc.y);
                    locs.push(obs[i].loc);
                    break;
                case "ghost":
                    drawGhost(obs[i].loc.x, obs[i].loc.y);
                    locs.push(obs[i].loc);
                    break;
                case "bean":
                    drawBean(obs[i].loc.x, obs[i].loc.y);
                    break;
                case "power":
                    drawPower(obs[i].loc.x, obs[i].loc.y);
                    break;
            }
        }
        if (data.gameOver == true) {
            clearInterval(intervalId);
            showBanner(2);
        }
    }, "json");
}

/**
 * Pass along the canvas dimensions, get the initialized GameBoard
 */
function canvasDims() {
    $.get("/canvas", {height: app.dims.height, width: app.dims.width},function (data,status) {
        score = data.score;
        document.getElementById("score").innerText = score;

        var obs = data.obs;
        locs = [];
        for (var i = 0; i < obs.length; i++) {
            switch (obs[i].type) {
                case "wall":
                    wallCanvas.drawWall(obs[i].loc.x, obs[i].loc.y, obs[i].size.x, obs[i].size.y);
                case "pacman":
                    app.drawPacman(obs[i].loc.x, obs[i].loc.y);
                    locs.push(obs[i].loc);
                    break;
                case "ghost":
                    app.drawGhost(obs[i].loc.x, obs[i].loc.y);
                    locs.push(obs[i].loc);
                    break;
                case "bean":
                    app.drawBean(obs[i].loc.x, obs[i].loc.y);
                    break;
                case "power":
                    app.drawPower(obs[i].loc.x, obs[i].loc.y);
                    break;
            }
        }
        showBanner(1);
        startMonitorKeyBoard();
    },"json");
}


function showBanner(id){
    var bannerDiv = $("#banner-div");
    var bannerImg = $("#img");
    if (id===1){
        bannerDiv.show();
        bannerImg.attr('src',"img/banner_start.png");
    }else if (id===2){
        bannerDiv.show();
        bannerImg.attr('src',"img/banner_restart.png");
    }else {
        bannerDiv.hide();
    }
}

/**
 * monitor the keys(space, left/up/right/down arrow) event
 */
function startMonitorKeyBoard() {
    $("body").keypress(function (event) {
        if (!gameStart && event.keyCode === 32){
            gameStart = true;
            start();
        }
    }).keydown(function (event) {
        if (!gameStart){
            return;
        }
        switch (event.keyCode) {
            case 37:
                move("left");
                break;
            case 38:
                move("up");
                break;
            case 39:
                move("right");
                break;
            case 40:
                move("down");
                break;
        }
    });
}
