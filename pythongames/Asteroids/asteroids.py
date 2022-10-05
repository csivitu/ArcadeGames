import pygame
import os

pygame.init()

screen = pygame.display.set_mode((1000, 600))

app_icon = pygame.image.load(os.path.join(
    os.path.dirname(__file__), '.', 'arcade-game.png'))

player_icon = pygame.image.load(os.path.join(
    os.path.dirname(__file__), '.', 'battleship.png'))

pygame.display.set_caption('Asteroids')
icon = pygame.image.load('arcade-game.png')

playerX = 500
playerY = 300

def player():
    screen.blit(player_icon , (playerX , playerY))

active = True
while active:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

    player()
    pygame.display.update()




