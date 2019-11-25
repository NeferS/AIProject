import numpy as np
import re
import copy
from bitsets import bitset
from bitstring import BitStream, BitArray
from operator import itemgetter


def buildMainMatrix():		
	
	matrix = np.empty((8, 8), dtype=object)
	
	
	flag = True
	bbLabel = 0	
	representationLabelChar = "A"
	representationLabelInt = 1
	
	for i in range(8):
		
		flag = not(flag)
		
		for j in range(8):
			
			if (flag):
			 	matrix[i][j] = (bbLabel, representationLabelChar + str(representationLabelInt))
			 	bbLabel += 1
			else:
				matrix[i][j] = (-1, "")
					
			flag = not(flag)
			representationLabelInt += 1
		
		representationLabelChar = chr(ord(representationLabelChar) + 1)
		representationLabelInt = 1
	
	return matrix

def calculateSupportMatrix(matrix, exitMoves):
	
	supportMatrix = np.empty((32, 32), dtype=object)
	

	for srcSquare in range(0, 32):
		
		srcMoves = exitMoves[srcSquare]
		#Ordino le mosse legali da srcSquare in modo crescente rispetto a dstSquare
		srcMoves = sorted(srcMoves,key=itemgetter(1))

		moveIndex = 0
		
		
		for dstSquare in range(0, 32):
			
			validMove = ()
			
			if dstSquare == srcSquare:
				direction = ""
				distance = 0
				
			else:	
				for move in srcMoves:
					if move[1] == dstSquare:
						validMove = move
						break
				
				if len(validMove) > 0 :
					direction = validMove[2]
					distance = validMove[3]
				else:
					direction = ""
					distance = -1
					
					
			supportMatrix[srcSquare][dstSquare] = (direction, distance)
		
					
			'''			
			if moveIndex < len(srcMoves):
				move = srcMoves[moveIndex]
				dst = move[1]
				if j >= dst:
					direction = move[2]
					distance = move[3]
					supportMatrix[srcSquare][dst] = (direction, distance)
					moveIndex += 1	
				elif srcSquare == j:
					supportMatrix[srcSquare][srcSquare] = ("", 0)
				else:
					supportMatrix[srcSquare][j] = ("", -1)
			elif srcSquare == j:
				supportMatrix[srcSquare][srcSquare] = ("", 0)
			else:
				supportMatrix[srcSquare][j] = ("", -1)
			'''

		
	return supportMatrix		


#A partire dalla rappresentazione a matrice, naturale per la scacchiera, genera una lista di liste di mosse.
#Ogni sottolista contiene tutti gli spostamenti possibili a partire da una particolare casella nera (srcSquare)
def bruteforceMoves(matrix):
	
	moves = []
	
	for i in range(8):
		for j in range(8):
			tmp = calculateMoves(matrix, i, j)
			if len(tmp) > 0:
				moves.append(tmp)

	return moves		
		
#Calcola tutti gli spostamenti possibili a partire da una certa casella nera di coordinate (x, y)
def calculateMoves(matrix, x, y):
	moves = []
	
	#Setup src square
	src = matrix[x][y][0]
	
	#Return no moves if a white square is selected
	if src == -1 :
		return moves
		
	
	#Evaluate N direction
	distance = 1
	i = x - 1
	j = y
	
	while i >= 0:
		dst = matrix[i][j][0]
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
		dst = matrix[i][j][0]
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
		dst = matrix[i][j][0]
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
		dst = matrix[i][j][0]
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
		dst = matrix[i][j][0]
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
		dst = matrix[i][j][0]
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
		dst = matrix[i][j][0]
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
		dst = matrix[i][j][0]
		if dst != -1:
			move = (src, dst, "SE", distance)
			moves.append(move)
		i += 1
		j += 1
		distance += 1
	
	return moves


#Classifica le exitMoves in mosse d'attacco, equivalenti per i due giocatori, e le non-capture moves,
#differenti per il bianco e il nero
def extractMoves(exitMoves):
	
	whiteNonCaptureMoves = []
	blackNonCaptureMoves = []
	captureMoves = []
	
	src = 0
		
	for moves in exitMoves:
		
		whiteSrcNonCaptureMoves = []
		blackSrcNonCaptureMoves = []
		srcCaptureMoves = []
		
		for n in range(1, 13):
			
			selectedWhiteNonCaptureMoves = []
			selectedBlackNonCaptureMoves = []
			selectedCaptureMoves = []
			
			
			for move in moves:
				if n >= move[3]:
					selectedCaptureMoves.append((move[1], move[2], move[3]))
					if move[2] != "W" and move[2] != "E" and move[2] != "S" and move[2] != "SW" and move[2] != "SE":
						selectedWhiteNonCaptureMoves.append((move[1], move[2], move[3]))
					elif move[2] != "W" and move[2] != "E" and move[2] != "N" and move[2] != "NW" and move[2] != "NE":
						selectedBlackNonCaptureMoves.append((move[1], move[2], move[3]))
			
			

			 
			whiteSrcNonCaptureMoves.append(selectedWhiteNonCaptureMoves)
			blackSrcNonCaptureMoves.append(selectedBlackNonCaptureMoves)
			srcCaptureMoves.append(selectedCaptureMoves)
		
		
			
		whiteNonCaptureMoves.append(whiteSrcNonCaptureMoves)
		blackNonCaptureMoves.append(blackSrcNonCaptureMoves)
		captureMoves.append(srcCaptureMoves)			
	
	
	
	return { "whiteNonCaptureMoves": whiteNonCaptureMoves, "blackNonCaptureMoves": blackNonCaptureMoves, "captureMoves": captureMoves }
		


	

def calculateMovesBitboards(classifiedMoves):
	
	movesBitboards = {}
	masterBitboard = []
	for i in range(31, -1, -1):
		masterBitboard.append(i)
	
	masterBitboard = tuple(masterBitboard)
	masterBitboard = bitset("bitboard", masterBitboard)
	
	for name in classifiedMoves:
		
		movesFromSquares = classifiedMoves[name]
		movesFromSquaresBitboards = []
		
		for boundedMoves in movesFromSquares:
			
			boundedMovesBitboards = []
			
			for moves in boundedMoves:
				
				squares = []
				
				for move in moves:
					squares.append(move[0])
					
				bitmask = masterBitboard(tuple(squares)).bits()
				bitboard = [BitArray('0b'+bitmask[24:32]),
							BitArray('0b'+bitmask[16:24]),
							BitArray('0b'+bitmask[8:16]),
							BitArray('0b'+bitmask[0:8])]
				
				boundedMovesBitboards.append(bitboard)
			
			movesFromSquaresBitboards.append(boundedMovesBitboards)
		
		movesBitboards[name] = movesFromSquaresBitboards
		
	return movesBitboards


		
def printJavaBitSetMatrix(bitsetMatrix, name):
	
	str = "final BitSet[][] " + name + " = {"
	str += "\n"
	
	for i in bitsetMatrix:
		
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


def printJavaDirectionMatrix(supportMatrix, name):
	
	formattedDirectionMatrix = "final String[][] " + name + " = {\n"
	
	for row in supportMatrix:
		
		formattedDirectionMatrix += "\t\t\t\t\t\t\t\t\t\t{ "
		for j in range(0,32) :
			
			formattedDirectionMatrix += "\"" + row[j][0] + "\", "
			 
		formattedDirectionMatrix += "\t\t\t\t\t\t\t\t\t\t},\n"

	formattedDirectionMatrix += "\t\t\t\t\t\t\t\t\t};\n"
	
	return formattedDirectionmatrix


def printJavaDistanceMatrix(supportMatrix, name):
	
	formattedDistanceMatrix = "final int[][] " + name + " = {\n"
	
	for row in supportMatrix:
		
		formattedDistanceMatrix += "\t\t\t\t\t\t\t\t\t\t{ "
		for j in range(0,32) :
			
			formattedDistanceMatrix += str(row[j][1]) + ", "
			 
		formattedDistanceMatrix += "\t\t\t\t\t\t\t\t\t\t},\n"

	formattedDistanceMatrix += "\t\t\t\t\t\t\t\t\t};\n"
	
	return formattedDistanceMatrix


#A partire dalla rappresentazione a matrice, naturale per la scacchiera, genera una lista di liste.
#Ogni sottolista contiene triple (direction, minDistance a partire da una particolare casella nera (srcSquare)
#exitMoves[i] lista di tuple che indicando le mosse possibili per portare pedine fuori dalla scacchiera dalla casella i.
#Ogni tupla indica una direzione possibile di uscita e il numero di pedine minimo che serve per raggiungerla.	
def bruteforceExitMoves(matrix):
	
	exitMoves = []
	
	for i in range(8):
		for j in range(8):
			tmp = calculateExitMoves(matrix, i, j)
			if len(tmp) > 0:
				exitMoves.append(tmp)

	return exitMoves

#Calcola tutte le mosse per portare fuori le pedine a partire da una certa casella nera di coordinate (x, y)
def calculateExitMoves(matrix, x, y):
	
	exitMoves = []
	
	#Setup src square
	src = matrix[x][y][0]
	
	#Return no exitMoves if a white square is selected (succede una riga si e una no)
	if src == -1 :
		return exitMoves
		
	
	
	#Evaluate N direction
	minDistance = 0
	i = x - 1
	j = y
	
	while i >= 0:
		i -= 1
		minDistance += 1
	minDistance += 1
	exitMoves.append(("N", minDistance))
	

	
	#Evaluate S direction
	minDistance = 0
	i = x + 1
	j = y
	
	while i < 8:
		i += 1
		minDistance += 1	
	minDistance += 1
	exitMoves.append(("S", minDistance))
		

	exitMoves.append(("W", -1))
	exitMoves.append(("E", -1))

	#Evaluate NW direction
	minDistance = 0
	i = x - 1
	j = y - 1
	
	while i >= 0 and j >= 0:
		i -= 1
		j -= 1
		minDistance += 1	
	minDistance += 1
	exitMoves.append(("NW", minDistance))
	
	#Evaluate NE direction
	minDistance = 0
	i = x - 1
	j = y + 1
	
	while i >= 0 and j < 8:
		i -= 1
		j += 1
		minDistance += 1
	minDistance += 1
	exitMoves.append(("NE", minDistance))	
		
	#Evaluate SW direction
	minDistance = 0
	i = x + 1
	j = y - 1
	
	while i < 8 and j >= 0:
		i += 1
		j -= 1
		minDistance += 1	
	minDistance += 1
	exitMoves.append(("SW", minDistance))
	
	#Evaluate SE direction
	minDistance = 0
	i = x + 1
	j = y + 1
	
	while i < 8 and j < 8:
		i += 1
		j += 1
		minDistance += 1
	minDistance += 1
	exitMoves.append(("SE", minDistance))
	
	
	return exitMoves

#Estrae le mosse di uscita delle pedine dalla scacchiera per il giocatore bianco e quello nero
'''
def extractOptimisedExitMoves(exitMoves):
	
	whiteExitMoves = []
	blackExitMoves = []
	
	for srcExitMoves in exitMoves:
		
		#sortedSrcExitMoves = sorted(srcExitMoves, key=itemgetter(1))
		#minDistance = sortedSrcExitMoves[0][1]
		#sortedSrcExitMoves = [i for i in sortedSrcExitMoves if i[1] == minDistance]
		sortedWhiteSrcExitMoves = [i for i in srcExitMoves 
								   if i[0] != "W" and i[0] != "E" and i[0] != "S" and i[0] != "SW" and i[0] != "SE"]
		sortedBlackSrcExitMoves = [i for i in srcExitMoves 
								   if i[0] != "W" and i[0] != "E" and i[0] != "N" and i[0] != "NW" and i[0] != "NE"]

						
		sortedWhiteSrcExitMoves = sorted(sortedWhiteSrcExitMoves, key = itemgetter(1))
		sortedBlackSrcExitMoves = sorted(sortedBlackSrcExitMoves, key = itemgetter(1))
		
		whiteExitMoves.append(sortedWhiteSrcExitMoves[0])
		blackExitMoves.append(sortedBlackSrcExitMoves[0])


	return { "whiteExitMoves": whiteExitMoves, "blackExitMoves": blackExitMoves }
		

def printOptimisedExitMovesDirections(classifiedExitMoves):
	
	name = "exitMovesDirections"
	output = "final String[][] " + name + " = {\n"
	output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\n"
	
	whiteExitMoves = classifiedExitMoves["whiteExitMoves"]
	for i in range(0, 32, 8):
		output += "\t\t\t\t\t\t\t\t\t\t "
		for j in range(0, 8) :
			
			output += "\"" + whiteExitMoves[i + j][0] + "\", "
			 
		output += "\n"
		

	output += "\t\t\t\t\t\t\t\t\t},\n"
	
	
	output += "\t\t\t\t\t\t\t\t\t\t{\n"
	
	blackExitMoves = classifiedExitMoves["blackExitMoves"]
	for i in range(0, 32, 8):
		output += "\t\t\t\t\t\t\t\t\t\t "
		for j in range(0, 8) :
			
			output += "\"" + blackExitMoves[i + j][0] + "\", "
			 
		output += "\n"
		
	output += "\t\t\t\t\t\t\t\t\t},\n"

	output += "\t\t\t\t\t\t\t\t\t};\n"
	
	
	return output
	
	
def printOptimisedExitMovesDistances(classifiedExitMoves):
	
	name = "exitMovesDistances"
	output = "final int[][] " + name + " = {\n"
	output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\n"
	
	whiteExitMoves = classifiedExitMoves["whiteExitMoves"]
	for i in range(0, 32, 8):
		output += "\t\t\t\t\t\t\t\t\t\t "
		for j in range(0, 8) :
			
			output += str(whiteExitMoves[i + j][1]) + ", "
			 
		output += "\n"
		

	output += "\t\t\t\t\t\t\t\t\t},\n"
	
	
	output += "\t\t\t\t\t\t\t\t\t\t{\n"
	
	blackExitMoves = classifiedExitMoves["blackExitMoves"]
	for i in range(0, 32, 8):
		output += "\t\t\t\t\t\t\t\t\t\t "
		for j in range(0, 8) :
			
			output += str(blackExitMoves[i + j][1]) + ", "
			 
		output += "\n"
		
	output += "\t\t\t\t\t\t\t\t\t},\n"

	output += "\t\t\t\t\t\t\t\t\t};\n"
	
	
	return output
'''

#Estrae le mosse di uscita delle pedine dalla scacchiera per il giocatore bianco e quello nero
def extractExitMoves(exitMoves):
	
	whiteExitMoves = []
	blackExitMoves = []
	count = 0
	
	
	for i in range(32):
		
		whiteSrcExitMoves = copy.copy(exitMoves[i])
		blackSrcExitMoves = copy.copy(exitMoves[i])
		for j in range(8):
			

			
			if whiteSrcExitMoves[j][0] == "W" or whiteSrcExitMoves[j][0] == "E" or  whiteSrcExitMoves[j][0] == "S" or whiteSrcExitMoves[j][0] == "SW" or whiteSrcExitMoves[j][0] == "SE":
				whiteSrcExitMoves[j] = (whiteSrcExitMoves[j][0], -1)
				
			if blackSrcExitMoves[j][0] == "W" or blackSrcExitMoves[j][0] == "E" or  blackSrcExitMoves[j][0] == "N" or blackSrcExitMoves[j][0] == "NW" or blackSrcExitMoves[j][0] == "NE":
				blackSrcExitMoves[j] = (blackSrcExitMoves[j][0], -1)	
									
			
		whiteExitMoves.append(whiteSrcExitMoves)
		blackExitMoves.append(blackSrcExitMoves)
		
	return { "whiteExitMoves": whiteExitMoves, "blackExitMoves": blackExitMoves }


def printExitMoves(classifiedExitMoves):
	
	output = "final int[][][] exitMoves = {\n"	
	output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\n" #Apro il bianco
	
	whiteExitMoves = classifiedExitMoves["whiteExitMoves"]
	
	for srcSquareMoves in whiteExitMoves:
		
		output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t{" #Apro src squares
		for exitMove in srcSquareMoves:
			output += str(exitMove[1]) + ", "
		
		output += "},\n"
		
	output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\n" 
	
	output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t{\n" #Apro il nero
	
	blackExitMoves = classifiedExitMoves["blackExitMoves"]
	
	for srcSquareMoves in blackExitMoves:
		
		output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t{" #Apro src squares
		for exitMove in srcSquareMoves:
			output += str(exitMove[1]) + ", "
		
		output += "},\n"
		
	output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t},\n" 
	output += "\t\t\t\t\t\t\t\t}\n"
	
	return output

def calculateEncodedSquares(matrix):
		
	encodedSquares = []
	
	for i in range(8):
		for j in range(8):
			if matrix[i][j][0] == -1:
				continue
			else:
				encodedSquares.append(matrix[i][j][1])
	
	return encodedSquares


def printEncodedSquares(encodedSquares):
	
	name = "encodedSquares"
	output = "final String[] " + name + " = {\n"
	output += "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"
	
	
	for i in range(0, 32, 8):
		output += "\t\t\t\t\t\t\t\t\t\t "
		for j in range(0, 8) :
			
			output += "\"" + str(encodedSquares[i + j]) + "\", "
			 
		output += "\n"
		

	output += "\t\t\t\t\t\t\t\t\t};\n"

		
	return output

def printInitialBoardState(bitboard):
	
	output = ""
	output += "BitSet.valueOf(new byte[] { " + str(bitboard[24:32]) + ", " + str(bitboard[16:24]) + ", " + str(bitboard[8:16]) + ", " + str(bitboard[0:8]) + ")};\n"  

	
	return output


def calculateWhiteInitialBoardState():
	
	masterBitboard = []
	for i in range(31, -1, -1):
		masterBitboard.append(i)
	
	masterBitboard = tuple(masterBitboard)
	masterBitboard = bitset("bitboard", masterBitboard)
	
	bitmask = masterBitboard(tuple([30])).bits()
	bitboard = [BitArray('0b'+bitmask[24:32]),
				BitArray('0b'+bitmask[16:24]),
				BitArray('0b'+bitmask[8:16]),
				BitArray('0b'+bitmask[0:8])]

	return bitboard


def calculateBlackInitialBoardState():
	
	masterBitboard = []
	for i in range(31, -1, -1):
		masterBitboard.append(i)
	
	masterBitboard = tuple(masterBitboard)
	masterBitboard = bitset("bitboard", masterBitboard)
	
	bitmask = masterBitboard(tuple([1])).bits()
	bitboard = [BitArray('0b'+bitmask[24:32]),
				BitArray('0b'+bitmask[16:24]),
				BitArray('0b'+bitmask[8:16]),
				BitArray('0b'+bitmask[0:8])]

	return bitboard


#Main
mainMatrix = buildMainMatrix()
rawMoves = bruteforceMoves(mainMatrix)
supportMatrix = calculateSupportMatrix(mainMatrix, rawMoves)
classifiedMoves = extractMoves(rawMoves)
classifiedMovesBitboards = calculateMovesBitboards(classifiedMoves)

rawExitMoves = bruteforceExitMoves(mainMatrix)
classifiedExitMoves = extractExitMoves(rawExitMoves)

encodedSquares = calculateEncodedSquares(mainMatrix)

#print rawExitMoves
#print classifiedExitMoves
print printExitMoves(classifiedExitMoves)

#print calculateBlackInitialBoardState()
#print printInitialBoardState(calculateInitialBoardState())
#print printEncodedSquares(encodedSquares)
#print printExitMovesDirections(classifiedExitMoves)
#print printOptimisedExitMovesDistances(classifiedExitMoves)

'''
javaFormattedBitboards = ""

for name in classifiedMovesBitboards:
	movesBitboards = classifiedMovesBitboards[name]
	javaFormattedBitboards += printJavaBitSetMatrix(movesBitboards, name)
	javaFormattedBitboards += "\n"
'''


'''
output = open("directionMatrix.txt", "w")
output.write(printJavaDirectionMatrix(supportMatrix, "directionMatrix"))
output.close()
'''

'''
output = open("distanceMatrix.txt", "w")
output.write(printJavaDistanceMatrix(supportMatrix, "distanceMatrix"))
output.close()
'''

