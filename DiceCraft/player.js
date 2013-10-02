function Player(newName, newColor, ai, depositIncome, block) {
	this.name = newName;
	this.color = newColor;
	this.isAI = ai;
	this.income = depositIncome; //assume only 1 dice on initiation
	this.blocks = block;

	this.addBlock = function (newBlock) {
		this.blocks.push(newBlock);
	};
	
	this.removeBlock = function(toBeRemovedBlock) {
		var indexToRemove = this.blocks.indexOf(toBeRemovedBlock);
		this.blocks.splice(indexToRemove, 1);
	};

	this.income = function() {
		for (var i = 0; i < this.blocks.length; i++) {
			if (this.blocks[i].isDeposit) {
				this.income += 1;
			} else {
				this.incom += 1/4;
			}
		}
	}
}