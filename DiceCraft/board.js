function Board(game, stage, blockData, players, imgObjArray, depositImg) {
	//imgObjArray sourced on higher level
	
	//constructor should make groups which adds Block and (diceImage inside Block)
	//constructor should also manage layering between groups
	this.players = players;
	this.game = game;
	var layer = new Kinetic.Layer({
		x: 0,
		y: 0,
	});
	this.allBlocks = createArray(blockData.length, blockData[0].length);
	var spawningBlocks = [];
	//helper function
	function createArray(length) {
		var arr = new Array(length || 0),
			i = length;

		if (arguments.length > 1) {
			var args = Array.prototype.slice.call(arguments, 1);
			while(i--) arr[length-1 - i] = createArray.apply(this, args);
		}

		return arr;
	}
	
	function within(a, b) {
		for(var i=0; i<b.length; i++) {
			if(a==b[i]) {
				return true;
			}
		}
		return false;
	}
	
	//helper function
	this.addPrevAdjBlock = function(newBlock, i, j, jMax) {
		this.addLeftBlock(newBlock, i, j);
		this.addTopLeftBlock(newBlock, i, j);
		this.addTopRightBlock(newBlock, i, j, jMax);
	}
	this.addLeftBlock = function(newBlock, i, j) {
		var leftBlock = this.allBlocks[i][j-1];
		if (leftBlock != null) {
			leftBlock.adjacentBlocks.push(newBlock);
			newBlock.adjacentBlocks.push(leftBlock);
		}
	};
	this.addTopLeftBlock = function(newBlock, i, j) {
		if(i<1) {return};
		var topLeftBlock;
		if ((i%2) == 0) {
			topLeftBlock = this.allBlocks[i-1][j-1];
		} else {
			topLeftBlock = this.allBlocks[i-1][j];
		}
		
		if (topLeftBlock != null) {
			topLeftBlock.adjacentBlocks.push(newBlock);
			newBlock.adjacentBlocks.push(topLeftBlock);
		}
	};
	this.addTopRightBlock = function(newBlock, i, j, jMax) {
		if(i<1) {return};
		var topRightBlock;
		if ((i%2) == 0) {
			if(j == jMax) {return;}
			topRightBlock = this.allBlocks[i-1][j];
		} else {
			topRightBlock = this.allBlocks[i-1][j+1];
		}
		
		if (topRightBlock != null) {
			topRightBlock.adjacentBlocks.push(newBlock);
			newBlock.adjacentBlocks.push(topRightBlock);
		}
	};
	
	this.spawnPlayer = function() {
		var spawningPoint;
		for(var i=0; i<this.players.length; i++) {
			if (spawningBlocks.length < 1) {
				alert("the game has no more spawning points!");
				return;
			}
			spawningPoint = Math.floor((Math.random()+0.5)*(spawningBlocks.length-1));
			this.players[i].takeBlock(spawningBlocks[spawningPoint]);
			spawningBlocks[spawningPoint].dice = 2;
			spawningBlocks[spawningPoint].postRedisplay();
			spawningBlocks.splice(spawningPoint, 1);
		}
	};
	
	this.blockByID = function(id) {
		var temp = id.split(",");
		var i = parseInt(temp[0]);
		var j = parseInt(temp[1]);
		return this.allBlocks[i][j];
	}
	for(var i=0; i<blockData.length; i++) {
		for(var j=0; j<blockData[i].length; j++) {
			if(blockData[i][j] != 0) {
				//row major
				var thisBoard = this;
				var newx = (i%2)*34.6+69.3*j;
				var newy = 60*i;
				//creating block
				
				var newBlock = new Block(i, j, imgObjArray, (blockData[i][j]!=1));
				newBlock.polygonShape.on('mouseover', function() {
					var thisBlock = thisBoard.blockByID(this.getId());
					if (!thisBoard.selectedBlock) {
						if(thisBlock.player != thisBoard.game.players[thisBoard.game.currentPlayer]) {
							return;
						}
					} else if(!within(thisBlock, thisBoard.selectedBlock.adjacentBlocks) && (thisBoard.selectedBlock.player!=thisBlock.player)) {
						return;
					} else if(thisBoard.selectedBlock == thisBlock) {
						return;
					};
					thisBlock.smallHighlightG();
					layer.draw();
				});

				newBlock.polygonShape.on('mouseout', function() {
					var thisBlock = thisBoard.blockByID(this.getId());
					if (thisBlock == thisBoard.selectedBlock) {
						return;
					}
					thisBlock.deHighlight();
					layer.draw();
				});
				newBlock.polygonShape.on('click', function() {
					var successAtk = false;
					var thisBlock = thisBoard.blockByID(this.getId());
					if (thisBlock.player == thisBoard.game.players[thisBoard.game.currentPlayer]) {
						//select route
						thisBoard.select(thisBlock);
					} else if (!thisBoard.selectedBlock) {
						return;
					} else if (within(thisBlock, thisBoard.selectedBlock.adjacentBlocks)) {
						//attack route
						successAtk = thisBoard.selectedBlock.attack(thisBlock);
						if (successAtk) {
							thisBlock.postRedisplay();
						}
						thisBoard.selectedBlock.postRedisplay();
						thisBoard.deselect();
					}
					layer.draw();
					if (successAtk) {
						thisBoard.game.loseCheck();
					}
				});
				
				//adding block in the array
				
				this.allBlocks[i][j] = newBlock;
				//connecting previously adjacent blocks
				this.addPrevAdjBlock(newBlock, i, j, blockData[0].length);//todo define addPrevAdjBlock
				
				//adding spawning points
				if(blockData[i][j] == 3) {
					spawningBlocks.push(newBlock);
				}
				//img processing
				var group = new Kinetic.Group({
					x: newx,
					y: newy,
					width: 69.3,
					height: 120
				});
				group.add(newBlock.polygonShape);
				
				if (newBlock.isDeposit) {
					var depositImage = new Kinetic.Image();
					depositImage.setAttrs({
						x: 0,
						y: 0,
						image: depositImg, //the img for deposit
						width: 69.3,
						height: 80,
						listening: false
					});
					group.add(depositImage);					
				}
				group.add(newBlock.diceImage);
				layer.add(group);
			} else {
				blockData[i][j] = null;
			}
		}
	}
	this.spawnPlayer();
	layer.drawHit();
	stage.add(layer);
	
	this.selectedBlock = null;
	
	this.select = function(block) {
		if (this.selectedBlock) {
			this.selectedBlock.deHighlight();
		}
		this.selectedBlock = block;
		block.largeHighlightG();
	};
	this.deselect = function() {
		if(this.selectedBlock) {
			this.selectedBlock.deHighlight();
			this.selectedBlock = null;
		}
	};
	this.draw = function() {
		layer.draw();
	};
	//this.postRedisplay = function() {};
	//this.distributeIncome = function() {};
	
}