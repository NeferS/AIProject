import numpy as np
import re
import copy
from bitsets import bitset
from bitstring import BitStream, BitArray

def calculateAllMoves(matrix):
	
	moves = []
	
	for i in range(8):
		for j in range(8):
			tmp = calculateMoves(matrix, i, j)
			moves.append(tmp)

	return moves		
		

def calculateMoves(matrix, x, y):
	moves = []
	
	#Setup src square
	src = matrix[x][y]
	
	#Return no moves if a white square is selected
	if src == -1 :
		return moves
		
	
	#Evaluate N direction
	distance = 1
	i = x - 1
	j = y
	
	while i >= 0:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "N", distance)
			moves.append(move)
		i -= 1
		distance += 1

	#Evaluate S direction
	distance = 1
	i = x + 1
	j = y
	
	while i < 8:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "S", distance)
			moves.append(move)
		i += 1
		distance += 1	
		
	#Evaluate W direction
	distance = 1
	i = x
	j = y - 1
	
	while j >= 0:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "W", distance)
			moves.append(move)
		j -= 1
		distance += 1	
		
	
	#Evaluate E direction
	distance = 1
	i = x
	j = y + 1
	
	while j < 8:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "E", distance)
			moves.append(move)
		j += 1
		distance += 1	
	
	#Evaluate NW direction
	distance = 1
	i = x - 1
	j = y - 1
	
	while i >= 0 and j >= 0:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "NW", distance)
			moves.append(move)
		i -= 1
		j -= 1
		distance += 1	

	#Evaluate NE direction
	distance = 1
	i = x - 1
	j = y + 1
	
	while i >= 0 and j < 8:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "NE", distance)
			moves.append(move)
		i -= 1
		j += 1
		distance += 1
		
	#Evaluate SW direction
	distance = 1
	i = x + 1
	j = y - 1
	
	while i < 8 and j >= 0:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "SW", distance)
			moves.append(move)
		i += 1
		j -= 1
		distance += 1	
		
	
	#Evaluate SE direction
	distance = 1
	i = x + 1
	j = y + 1
	
	while i < 8 and j < 8:
		dst = matrix[i][j]
		if dst != -1:
			move = (src, dst, "SE", distance)
			moves.append(move)
		i += 1
		j += 1
		distance += 1
	
	return moves
	

def calculatePattern(allMoves):
	#Setup bitboard skeleton
	bitboard = []
	for i in range(31, -1, -1):
		bitboard.append(i)
	
	bitboard = tuple(bitboard)
	bitboard = bitset("bitboard", bitboard)
	
	
	whiteMovePattern = []
	blackMovePattern = []
	attackPattern = []
	
	for moves in allMoves:
		
		whitePieceMovePattern = []
		blackPieceMovePattern = []
		pieceAttackPattern = []
		
		for n in range(1, 13):
			
			selectedWhiteMoveSquares = []
			selectedBlackMoveSquares = []
			selectedAttackSquares = []
			
			
			for move in moves:
				if n >= move[3]:
					selectedAttackSquares.append(move[1])
					if move[2] != "W" and move[2] != "E" and move[2] != "S" and move[2] != "SW" and move[2] != "SE":
						selectedWhiteMoveSquares.append(move[1])
					elif move[2] != "W" and move[2] != "E" and move[2] != "N" and move[2] != "NW" and move[2] != "NE":
						selectedBlackMoveSquares.append(move[1])
			
			
			
			bitmask = bitboard(tuple(selectedWhiteMoveSquares)).bits()
			whitePieceMoveMask = [BitArray('0b'+bitmask[0:8]), 
								  BitArray('0b'+bitmask[8:16]), 
								  BitArray('0b'+bitmask[16:24]), 
								  BitArray('0b'+bitmask[24:32])]
			
			bitmask = bitboard(tuple(selectedBlackMoveSquares)).bits()
			blackPieceMoveMask = [BitArray('0b'+bitmask[0:8]), 
								  BitArray('0b'+bitmask[8:16]), 
								  BitArray('0b'+bitmask[16:24]), 
								  BitArray('0b'+bitmask[24:32])]
			
			bitmask = bitboard(tuple(selectedAttackSquares)).bits()
			pieceAttackMoveMask = [BitArray('0b'+bitmask[0:8]), 
								  BitArray('0b'+bitmask[8:16]), 
								  BitArray('0b'+bitmask[16:24]), 
								  BitArray('0b'+bitmask[24:32])]
	
			whitePieceMovePattern.append(whitePieceMoveMask)
			blackPieceMovePattern.append(blackPieceMoveMask)
			pieceAttackPattern.append(pieceAttackMoveMask)
		
		whiteMovePattern.append(whitePieceMovePattern)
		blackMovePattern.append(blackPieceMovePattern)
		attackPattern.append(pieceAttackPattern)			
	
	
	return [whiteMovePattern, blackMovePattern, attackPattern]
		
		
def printJavaBitSetMatrix(pattern, name):
	
	str = "final BitSet[][] " + name + " = {"
	str += "\n"
	
	for i in pattern:
		
		str += "\t\t\t\t\t\t\t\t\t\t"
		str += "{"
		str += "\n"
		
		for j in i :
			str += "\t\t\t\t\t\t\t\t\t\t\t"		#(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
			str += "BitSet.valueOf(new byte[] { " 
			
			for k in j:
				str += "(byte) 0x"
				str += k.hex
				str += ", "
			  
			str += "}),"
			str += "\n"
			 
		str += "\t\t\t\t\t\t\t\t\t\t"
		str += "},"
		str += "\n"

	str += "\t\t\t\t\t\t\t\t\t"
	str += "};"
	str += "\n"
	
	return str
		
checkers = np.full((8, 8), -1)


flag = True
label = 0

for i in range(8):
	flag = not(flag)
	for j in range(8):
		 if (flag):
		 	checkers[i][j] = label
		 	label += 1
		 flag = not(flag)
 

allMoves = calculateAllMoves(checkers)
pattern = calculatePattern(allMoves)

#print printJavaBitSetMatrix(patterns[0], "whiteMovePattern")

javaFormattedPattern = printJavaBitSetMatrix(pattern[0], "whiteMovePattern")
javaFormattedPattern += "\n"
javaFormattedPattern += printJavaBitSetMatrix(pattern[1], "blackMovePattern")
javaFormattedPattern += "\n"
javaFormattedPattern += printJavaBitSetMatrix(pattern[2], "attackPattern")
javaFormattedPattern += "\n"

output = open("pattern.txt", "w")
output.write(javaFormattedPattern)
output.close()


