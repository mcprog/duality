import pygame

__author__ = 'mcprog'


class Player:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.width = 32
        self.height = 32
        self.vx = 0
        self.vy = 0
        self.falling = True
        self.onGround = False
        self.rotation = 0

    def jump(self, force):
        if (self.onGround):
            self.vy += force
            self.y += self.vy
            self.onGround = False

    def move(self, vx):
        self.vx = vx
        self.x += vx

    def update(self, gravity, blockList):
        if self.vy > 0:
            self.falling = True
        collision = False
        blockX, blockY = 0, 0
        for block in blockList:
            collision = self.detectCollision(self.x, self.y, self.width, self.height, block.x, block.y, block.width,
                                             block.height)
            if collision:
                blockX = block.x
                blockY = block.y  # may collide in the middle of the block so save for snapping later
                break  # don't want collision to be changed to False next iteration

        if collision:
            vx = 0
            if self.falling:
                self.falling = False
                self.onGround = True
                #self.vy = 0
                self.y = blockY - self.height  # snap to nearest block
        if not self.onGround:
            self.vy += gravity
        self.y += self.vy

    def render(self, display):
        pygame.draw.rect(display, (0, 200, 0), [self.x, self.y, self.width, self.height])

    @staticmethod
    def detectCollision(x1, y1, w1, h1, x2, y2, w2, h2):
        xWithin = False
        yWithin = False
        if x2 + w2 >= x1 >= x2 or x2 + w2 >= x1 + w1 >= x2:  # if x1 is within right and left edges and y1 is within bottom and top
            xWithin = True
        if y2 + h2 >= y1 >= y2 or y2 + h2 >= y1 + h1 > y2:
            yWithin = True
        return xWithin and yWithin
