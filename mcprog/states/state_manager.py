

__author__ = 'mcprog'


class StateManager:

    STATES = [None, None]
    SPLASH_ID = 0
    MENU_ID = 1

    def __init__(self, start_state_id):
        self.state = StateManager.STATES[start_state_id]
        self.state.enter()

    def switch(self, new_state_id):
        self.state.leave()
        self.state = StateManager.STATES[new_state_id]
        self.state.enter()
            

