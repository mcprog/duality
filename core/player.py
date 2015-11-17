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

    def jump(self, force):
        self.vy += force
        self.y += self.vy

    def move(self, vx):
        self.vx = vx
        self.x += vx

    def update(self, gravity):
        self.vy += gravity
        self.y += self.vy

    def render(self, display):
        pygame.draw.rect(display, (0, 0, 0), [self.x, self.y, self.width, self.height])