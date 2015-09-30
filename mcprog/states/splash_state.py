from mcprog.states.state_manager import StateManager
from mcprog.states.game_state import GameState
from mcprog.library.strings import WIN_HEIGHT
import pygame
import os


__author__ = 'mcprog'


class SplashState(GameState):

    def __init__(self):
        super(SplashState, self).__init__()
        self.img = pygame.image.load(os.path.join('data/images', 'opensource.png'))
        print("successfully loaded image")

    def draw(self):
        return pygame.transform.scale(self.img.convert_alpha(), (WIN_HEIGHT, WIN_HEIGHT))

    def should_switch_state(self):
        return StateManager.MENU_ID if self.state_time > 3000 else -1