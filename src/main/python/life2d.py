length=64
pattern=30

data = [0] * length
data[length/2]=1

def getOctet(data, offset):
	value=0
	for i in data[max(0,offset-1):min(length,offset+2)]:
		value=value*2 + i 
	return value

def getCellValue(pattern, octet):
	value=0
	if(octet>1):
		value=(pattern >> (octet-1)) & 1
	return value
	
def nextGeneration(data, pattern):
	gen=[0]*length
	for i in range(1, len(data)):
		if(getCellValue(pattern, getOctet(data, i))==1):
			gen[i]=1
	return gen

def printable(data):
	output=""
	for i in range(0, len(data)):
		if(data[i]==1):
			output=output+"*"
		else:
			output=output+" "
	return output

#print getOctet(data, 30)
#print getCellValue(30, 2)
#print nextGeneration(data, pattern)

for i in range(0,len(data)/2):
	print printable(data)
	data=nextGeneration(data, pattern)