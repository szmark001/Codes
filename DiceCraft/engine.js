function Game() {
	this.bgColor = '#000000';
	this.player = [];
	this.board = {};
	this.currentPlayer = 0;
	this.numPlayer = {};
	this.depositIncome = 1; //income per dice on a deposit
	
	//topLevel functions
	this.addPlayer = function() {}
;	this.init = function() {};
	this.advanceTurn = function() {};
	this.gameOver = function() {};
	
	//lowLevel functions
	//init
	this.initBoard = function(blockData, this.player) {}; //todo blockData source
	this.initUI = function() {};
	this.initMouseListener = function() {};
	
	//advanceTurn
	this.loseCheck = function() {};//inside gameOverCheck
	this.gameOverCheck = function() {};
	this.advanceRound = function() {};
	
	//gameOver
	this.gameOver = function() {};
}

var game = new Game();
game.addPlayer();