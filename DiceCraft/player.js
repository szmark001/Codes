function Player(newName, newColor, ai) {
	this.name = newName;
	this.color = newColor;
	this.isAI = ai;
	this.blocks = [];
	this.depositIncome = 1;
	this.normalIncome = 0.25;

	this.removeBlock = function(toBeRemovedBlock) {
		var indexToRemove = this.blocks.indexOf(toBeRemovedBlock);
		this.blocks.splice(indexToRemove, 1);
		toBeRemovedBlock.player = null;
	};
	
	this.takeBlock = function (newBlock) {
		if(newBlock.player) {
			newBlock.player.removeBlock(newBlock);
		}
		this.blocks.push(newBlock);
		newBlock.player = this;
		newBlock.postRedisplay();
	};

	this.income = function() {
		var rtn = 0;
		for (var i = 0; i < this.blocks.length; i++) {
			if (this.blocks[i].isDeposit) {
				rtn += this.blocks[i].dice*this.depositIncome;
			} else {
				rtn += this.normalIncome;
			}
		}
		return rtn;
	};
	
	this.distributeIncome = function() {
		var currentIncome = Math.floor(this.income());
		var blocksClone = this.blocks.slice(0);
		for (var i=0; i<currentIncome; i++) {
			if(blocksClone.length == 0) {
				return;
			}
			var toBeIncre = Math.floor(Math.random()*blocksClone.length);
			if (blocksClone[toBeIncre].dice == 8) {
				blocksClone.splice(toBeIncre,1);
				i--;
				continue;
			}
			blocksClone[toBeIncre].diceIncre(1);
			blocksClone[toBeIncre].postRedisplay();
		}
	};
}