function Game() {
	this.players = [];
	this.board = {};
	this.currentPlayer = 0;
	this.numPlayer = 0;
	this.blockData = {};
	this.round = 1;
	this.map = testMap03;
	//lowLevel functions
	//init
	this.initBoard = function(blockData) {
		this.board = new Board(this, stage, blockData, this.players, diceImages, depositImg);
	};
	this.initUI = function() {
		$('#currentPlayer').text('Current Player: ' + this.players[this.currentPlayer].name);
		$('#currentPlayer').css('background-color', this.players[this.currentPlayer].color);
	};
	this.initMouseListener = function() {
		var thisGame = this;
		document.getElementById('finishMove').addEventListener('click', function() {
			thisGame.advanceTurn();
		}, false);
	};
	//advanceTurn
	this.loseCheck = function() {
		for (var i=0; i<this.players.length; i++) {
			if (this.players[i].blocks.length==0) {
				alert(this.players[i].name + ' has been knocked out!');
				this.players.splice(i, 1);
				this.gameOverCheck();
				return;
			}
		}
	};
	
	this.gameOverCheck = function() {
		if (this.players.length==1) {
			alert(this.players[0].name + ' is the winner!');
		}
	};
	this.advanceRound = function() {
		for (var i=0; i<this.players.length; i++) {
			this.players[i].distributeIncome();
		}
		this.round++;
		$('#round').text('Round: ' + this.round);
		this.board.draw();
	};
	
	//topLevel functions
	this.addPlayer = function(name, color, ai) {
		var newPlayer = new Player(name, color, ai);
		this.players.push(newPlayer);
	};
	
	this.init = function() {
		//debug input
		this.initBoard(this.map);
		this.initUI();
		this.initMouseListener();
	};
	this.advanceTurn = function() {
		if(this.currentPlayer >= this.players.length-1) {
			this.advanceRound();
			this.currentPlayer = 0;
		} else {
			this.currentPlayer++;
		}
		this.board.deselect();
		this.board.draw();
		$('#currentPlayer').text('Current Player: ' + this.players[this.currentPlayer].name);
		$('#currentPlayer').css('background-color', this.players[this.currentPlayer].color);
	};
	//this.gameOver = function() {};
	//this.postRedisplay = function() {};
	
	
	//gameOver
	//this.gameOver = function() {};
}

var stage = new Kinetic.Stage({
        container: 'container',
        width: 1000,
        height: 750
});

var onLoaded = function() {
	if (depositImgLoaded) {
		var game = new Game();

		game.addPlayer('player1', '#00F2FF', false);
		game.addPlayer('player2', '#FFA600', false);
		game.init();
	}
};

depositImg.onload = function() {
	depositImgLoaded = true;
	onLoaded();
};