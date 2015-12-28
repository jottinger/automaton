#!/usr/bin/python

class SimpleRenderer:
    def render(self, generation):
        output = ""
        for j in range(1, len(generation.data)):
            if generation.data[j] == 1:
                output += "*"
            else:
                output += " "
        print output


from PIL import Image,ImageMode,ImageDraw

class MonochromeRenderer:
    def __init__(self, width, height):
        self.width=width
        self.height=height
        self.image=Image.new('L',(width, height),255)
        self.draw=ImageDraw.Draw(self.image)

    def render(self, generation):
        for j in range(1, len(generation.data)):
            if generation.data[j] == 1:
        		self.draw.line([(j,generation.generation),(j,generation.generation)],fill=0)

    def write(self, filename):
        self.image.save(filename, "PNG")
        del self.draw

class Automaton2D:
    def __init__(self, l=128, p=171):
        self.length = l
        self.pattern = p
        self.data = [0] * self.length
        self.data[self.length / 2] = 1
        self.generation=0

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
            if self.cellvalue(val) == 1:
                gen[j] = 1
        self.data = gen
        self.generation+=1

import sys
import getopt

def main(argv):
    length=128
    pattern=171
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
    r=MonochromeRenderer(length,length/2)
    for i in range(0, length/2):
        r.render(d)
        d.nextgeneration()
    r.write("bw-"+str(pattern)+".png")
    
if __name__ == "__main__":
   main(sys.argv[1:])

