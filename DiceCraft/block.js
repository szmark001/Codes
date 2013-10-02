Block.prototype = new Kinetic.Polygon();//extending Polygon class from KineticJS

function Block(x, y, imgObjectArray, isDeposit) {
	//x, y are at the top left corner of the block
	//imageArray is sourced on higher level
	this.player = {};
	this.dice = 0;
	this.isDeposit = isDeposit;
	this.imgObjectArray = imgObjectArray;
	
	Kinetic.Polygon.apply(this); //calling the base constructor
	this.points = [];//TODO
	this.fill = '';//TODO default color
	this.stroke = '';//default no stroke
	this.strokeWidth: 5;
	this.x = x;
	this.y = y;
	this.width = 40;
	this.height = 80;
	
	this.diceImage = new Kinetic.Image();
	this.diceImage.x = 0;
	this.diceImage.y = 0;
	this.diceImage.image = imgObjectArray[0]; //0 index should point to an empty img
	this.diceImage.width = 40;
	this.diceImage.height = 80;
	
	//method that update the current state of the block to the screen
	this.drawBlock = function() {
		if ((this.dice > 8) || (this.dice < 0)) {alert('imgObjArray out of bound')};
		this.diceImage.image = this.imgObjectArray[this.dice];
		this.fill = this.player.color;
	};

	this.throwDice = function(diceNum) {
		//TODO add throwDice animation here
		var rtn = 0;
		for (var i=0; i<diceNum; i++) {
			rtn += Math.ceil(Math.random()*8);
		}
		return rtn;
	};

	this.attack = function(targetBlock) {
		var attackStr, defendStr;
		attackStr = this.throwDice(this.dice);
		defendStr = targetBlock.throwDice(targetBlock.dice);
		if (attackStr > defendStr) {
			targetBlock.player = this.player;
			targetBlock.diceSet(this.dice-1);
			this.diceReset();
			return true;
		} else {
			this.diceReset();
			return false;
		}
		//animation updated on higher level
	};
	
	this.diceReset = function() {
		this.dice = 1;
	}; //reduce to 1 dice when attack failed
	
	this.diceIncre = function(amount) {
		this.dice += amount;
	};

	this.diceSet = function(amount) {
		this.dice = amount;
	}; //set dice to arbitrary amount
}