import pygame
import mcprog.library.strings as STRINGS
from mcprog.states.state_manager import StateManager
from mcprog.states.splash_state import SplashState
from mcprog.states.menu_state import MenuState
import mcprog.utility.position_helpers as pos_help

__author__ = "mcprog"

if not pygame.font: print("Warning, fonts disabled")
if not pygame.mixer: print("Warning, sound disabled")


class DualityGame:

    def __init__(self):
        pygame.init()
        self.gameDisplay = pygame.display.set_mode((STRINGS.WIN_WIDTH, STRINGS.WIN_HEIGHT))

        StateManager.STATES[StateManager.SPLASH_ID] = SplashState()
        StateManager.STATES[StateManager.MENU_ID] = MenuState()
        self.state_manager = StateManager(StateManager.SPLASH_ID)

        self.clock = pygame.time.Clock()
        self.runGame = True
        self.dt = 0

    def main_loop(self):
        while self.runGame:
            self.tick()
            self.update()
            self.handle_events()
            self.draw()

    def update(self):
        self.state_manager.state.update(self.dt)
        new_state_id = self.state_manager.state.should_switch_state()
        if new_state_id != -1:
            self.state_manager.switch(new_state_id)

    def handle_events(self):
        for ev in pygame.event.get():
            if ev.type == pygame.QUIT:
                self.runGame = False

    def draw(self):
        state_surface = self.state_manager.state.draw()
        if (state_surface != None):
            state_surface_center = (
                    pos_help.center_horizontally(state_surface.get_width()),
                    pos_help.center_vertically(state_surface.get_height())
            )
            self.gameDisplay.blit(state_surface, state_surface_center)
        pygame.display.update()

    def tick(self):
        self.dt = self.clock.tick(STRINGS.FPS)

if __name__ == '__main__':
    game = DualityGame()
    game.main_loop()

pygame.quit()
quit()