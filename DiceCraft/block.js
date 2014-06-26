function Block(i,j,imgObjectArray, isDeposit) {
	//x, y are at the top left corner of the block
	//imageArray is sourced on higher level
	this.player = null;
	this.dice = 0;
	this.isDeposit = isDeposit;
	this.imgObjectArray = imgObjectArray;
	this.adjacentBlocks = [];
	
	//Kinetic.RegularPolygon.apply(this); //calling the base constructor
	this.polygonShape = new Kinetic.RegularPolygon();
	this.polygonShape.setAttrs({
		id: i.toString() +','+ j.toString(),
		sides: 6,
		radius: 36,
		fill: '#C4BA9B',//TODO default color
		stroke: '#918FBA',//default no stroke
		strokeWidth: 5,
		x: 34.6,
		y: 40
	});
	
	this.diceImage = new Kinetic.Image();
	this.diceImage.setAttrs({
		x: 0,
		y: -40,
		image: this.imgObjectArray[0], //0 index should point to an empty img
		width: 69.3,
		height: 120,
		listening: false
	});
	
	//method that update the current state of the block to the screen
	this.postRedisplay = function() {
		if ((this.dice > 8) || (this.dice < 0)) {alert('imgObjArray out of bound')};
		this.diceImage.setImage(this.imgObjectArray[this.dice]);
		if(this.player) {
			this.polygonShape.setFill(this.player.color);
		}
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
		if(this.dice < 2) {
			return false;
		}
		attackStr = this.throwDice(this.dice);
		defendStr = targetBlock.throwDice(targetBlock.dice);
		if (attackStr > defendStr) {
			this.player.takeBlock(targetBlock);
			targetBlock.diceSet(this.dice-1);
			this.diceReset();
			return true;
		} else {
			this.diceReset();
			return false;
		}
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
	this.smallHighlightG = function() {
		this.polygonShape.setStroke('#36E387');
		this.polygonShape.setStrokeWidth('5');
	};
	this.largeHighlightG = function() {
		this.polygonShape.setStroke('#31B56F');
		this.polygonShape.setStrokeWidth('9');
	};
	this.deHighlight = function() {
		this.polygonShape.setStroke('#918FBA');
		this.polygonShape.setStrokeWidth('5');
	};
}