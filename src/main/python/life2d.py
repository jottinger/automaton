class Automaton2D:
    def __init__(self, l=64, p=30):
        self.length = l
        self.pattern = p
        self.data = [0] * self.length
        self.data[self.length / 2] = 1

    def octet(self, offset):
        value = 0
        for j in self.data[max(0, offset - 1):min(self.length, offset + 2)]:
            value = value * 2 + j
        return value

    def cellvalue(self, octet):
        value = 0
        if octet > 1:
            value = (self.pattern >> (octet - 1)) & 1
        return value

    def formatted(self):
        output = ""
        for j in range(0, len(self.data)):
            if self.data[j] == 1:
                output += "*"
            else:
                output += " "
        return output

    def nextgeneration(self):
        gen = [0] * self.length
        for j in range(1, len(self.data)):
            if self.cellvalue(self.octet(j)) == 1:
                gen[j] = 1
        self.data = gen

d = Automaton2D()
for i in range(0, 30):
    d.nextgeneration()
    print d.formatted()
