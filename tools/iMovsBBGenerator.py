import numpy as np
import re
import copy
from bitsets import bitset


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
 
print ""	
print "Checkers (-1 indica caselle bianche, i numeri progressivi indicano le caselle nere)"
print ""

print checkers

allMoves = calculateAllMoves(checkers)

print ""
print "Lista di tutti i movimenti possibili da tutte le posizioni. Ogni tupla rappresenta uno di questi movimenti"
print "Ogni tupla e' composta da (casella di partenza, casella di destinazione, direzione, distanza)"
print ""


for moves in allMoves:
	for move in moves:
		print move
		
		
		
#Setup bitboard skeleton
bitboard = []
for i in range(31, -1, -1):
	bitboard.append(i)

bitboard = tuple(bitboard)
bitboard = bitset("bitboard", bitboard)
		
while True:
	input = raw_input("Inserisci posizione e numero di pedine separate da , (ctrl+z per uscire): src,n -> ")
	validInput = re.search("\d,\d", input)
	
	if validInput == None:
		print ""
		print "fai attenzione, finocchio"
		print ""
		continue
	
	input = input.split(",")
	src = int(input[0])
	n = int(input[1])
	if (src < 0 or src > 31 or n < 1 or n > 12):
		print "fai attenzione, finocchio"
		continue
	
	selectedMoves = []
	selectedSquares = []
	
	for moves in allMoves:
		if len(moves) > 0 and moves[0][0] == src:
			selectedMoves = copy.copy(moves)
			break
			
	for move in selectedMoves:
		if n < move[3]:
			continue
		else:
			selectedSquares.append(move[1])
	
	tmpCheckers = copy.deepcopy(checkers)
	selectedSquares.sort()
	k = 0
	
	for i in range(8):
		for j in range(8):
			if tmpCheckers[i][j] == -1:
				tmpCheckers[i][j] = 0
			elif tmpCheckers[i][j] == src:
				tmpCheckers[i][j] = -1
			elif len(selectedSquares) > 0 and k < len(selectedSquares) and tmpCheckers[i][j] == selectedSquares[k]:
				tmpCheckers[i][j] = 1
				selectedSquares[k]
				k += 1
			else:
				tmpCheckers[i][j] = 0
	
	
			
	print ""			
	print "Bitboard : " + bitboard.supremum.intersection(bitboard(tuple(selectedSquares))).bits()
	print ""
	print "Checkers (-1 indica la posizione scelta, 1 le posizioni raggiungibili, 0 tutte le altre)"
	print ""
	print tmpCheckers
	print ""
	


