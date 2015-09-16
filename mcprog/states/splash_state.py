from mcprog.states.game_state import GameState
from mcprog.library.strings import WIN_HEIGHT
import pygame
import os


__author__ = 'mcprog'


class SplashState(GameState):

    def __init__(self):
        self.img = pygame.image.load(os.path.join('data/images', 'opensource.png'))
        print("successfully loaded image")

    def draw(self):
        pygame.draw
        return pygame.transform.scale(self.img.convert_alpha(), (WIN_HEIGHT, WIN_HEIGHT))
