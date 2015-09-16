from mcprog.library.strings import WIN_WIDTH, WIN_HEIGHT

__author__ = 'mcprog'


def center_horizontally(surface_width):
    return (WIN_WIDTH - surface_width) / 2.0


def center_vertically(surface_height):
    return (WIN_HEIGHT - surface_height) / 2.0
