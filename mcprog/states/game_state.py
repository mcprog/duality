__author__ = 'mcprog'


class GameState:

    def __init__(self):
        self.state_time = 0

    def enter(self):
        self.state_time = 0
        print("Entered " + self.__class__.__name__)

    def leave(self):
        pass

    def update(self, dt):
        self.state_time += dt

    def handle_events(self):
        pass

    def draw(self):
        pass

    def should_switch_state(self):
        return -1
