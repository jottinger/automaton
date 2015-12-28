#!/usr/bin/python

class Automaton2D:
    def __init__(self, l=128, p=171):
        self.length = l
        self.pattern = p
        self.data = [0] * self.length
        self.data[self.length / 2] = 1

    def octet(self, offset):
        value = 0
        for j in range(-1, 2):
        	index=offset+j
        	value=value*2
        	if(index>0 and index<self.length):
        		value=value+self.data[index]
        return value

    def cellvalue(self, octet):
        value = 0
        if octet > 0:
	        value = (self.pattern >> octet-1) & 1
        return value

    def formatted(self):
        output = ""
        for j in range(1, len(self.data)):
            if self.data[j] == 1:
                output += "*"
            else:
                output += " "
        return output

    def nextgeneration(self):
		gen = [0] * self.length
		for j in range(0, len(self.data)):
			val=self.octet(j)
#			print j," ",val," ",self.cellvalue(val)
			if self.cellvalue(val) == 1:
				gen[j] = 1
		self.data = gen

import sys
import getopt

def main(argv):
	length=128
	pattern=30
	try:
	  opts, args = getopt.getopt(argv,"hl:p:",["length=","pattern="])
	except getopt.GetoptError:
	  print 'life2d.py -l <length> -p <pattern>'
	  sys.exit(2)
	for opt, arg in opts:
	  if opt == '-h':
	     print 'life2d.py -l <length> -p <pattern>'
	     sys.exit()
	  elif opt in ("-l", "--length"):
	     try:
	     	length = int(arg)
	     except ValueError:
	     	print "length is not numeric"
	     	sys.exit()
	  elif opt in ("-p", "--pattern"):
	     try:
	     	pattern = int(arg)
	     except ValueError:
	     	print "pattern is not numeric"
	     	sys.exit()
	d = Automaton2D(length, pattern)
	for i in range(0, length/2):
	    print d.formatted()
	    d.nextgeneration()

if __name__ == "__main__":
   main(sys.argv[1:])

