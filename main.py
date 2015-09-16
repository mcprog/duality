import pygame
import mcprog.library.strings as STRINGS
from mcprog.states.state_manager import StateManager
from mcprog.states.splash_state import SplashState
import mcprog.utility.position_helpers as pos_help

__author__ = "mcprog"

if not pygame.font: print("Warning, fonts disabled")
if not pygame.mixer: print("Warning, sound disabled")




class DualityGame:

    def __init__(self):
        pygame.init()
        self.gameDisplay = pygame.display.set_mode((STRINGS.WIN_WIDTH, STRINGS.WIN_HEIGHT))

        self.state_manager = StateManager(SplashState())

        self.clock = pygame.time.Clock()
        self.runGame = True

    def main_loop(self):
        while self.runGame:
            self.update()
            self.handle_events()
            self.draw()
            self.tick()

    def update(self):
        pass

    def handle_events(self):
        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                self.runGame = False

    def draw(self):
        state_surface = self.state_manager.state.draw()
        state_surface_center = (
                pos_help.center_horizontally(state_surface.get_width()),
                pos_help.center_vertically(state_surface.get_height())
        )
        self.gameDisplay.blit(state_surface, state_surface_center)
        pygame.display.update()

    def tick(self):
        self.clock.tick(STRINGS.FPS)

if __name__ == '__main__':
    game = DualityGame()
    game.main_loop()

pygame.quit()
quit()