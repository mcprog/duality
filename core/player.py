__author__ = 'mcprog'


class Player:

    def __index__(self, loc=(0, 0)):
        self.x = 5
        self.y = 6

    def move(self, loc):
        self.x = loc[0]
        self.y = loc[1]

    def move(self, x, y):
        self.x = x;
        self.y = y;

    def getX(self):
        return self.x;