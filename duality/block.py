import pygame

__author__ = 'mcprog'


class Block:

    def __init__(self, x, y):
        self.x = x;
        self.y = y;
        self.width = 32
        self.height = 32

    def render(self, display):
        pygame.draw.rect(display, (0, 0, 0), [self.x, self.y, self.width, self.height])
