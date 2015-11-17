import pygame
from core import color
from core import player

__author__ = 'mcprog'

# inits all of the pygame modules
print(pygame.init())  # returns a tuple that says whether successfully

gameDisplay = pygame.display.set_mode((1024, 600))
pygame.display.set_caption('Duality')

FPS = 60
WALK_SPEED = 5
RUN_SPEED = 10
GRAVITY = .5
mx = 0
my = 0
dx = WALK_SPEED
dy = GRAVITY
left = False
right = False
pygame.display.update()  # init update

# game loop
runGame = True
clock = pygame.time.Clock()

player = player.Player(0, 0)

while runGame:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            runGame = False
            break
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_a:
                left = True
            if event.key == pygame.K_d:
                right = True
            if event.key == pygame.K_LSHIFT:
                dx = RUN_SPEED
            if event.key == pygame.K_SPACE:
                player.jump(-20)
        elif event.type == pygame.KEYUP:
            if event.key == pygame.K_a:
                left = False
            if event.key == pygame.K_d:
                right = False
            if event.key == pygame.K_LSHIFT:
                dx = WALK_SPEED
    if left and not right:
        mx -= dx
    elif right and not left:
        mx += dx
    my += dy
    gameDisplay.fill(color.GRAY75)
    #pygame.draw.rect(gameDisplay, color.YELLOW, [mx, my, 64, 64])
    player.update(GRAVITY)
    player.render(gameDisplay)
    pygame.display.update()

    clock.tick(FPS)

pygame.quit()  # quits pygame
quit()  # quits python
