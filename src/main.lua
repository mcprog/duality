GS = require "hump.gamestate"

function love.load()
	SPLASH = require "states.splash"
	MENU = require "states.menu"
	CREDITS = require "states.credits"

	GS.registerEvents()
	GS.switch(SPLASH)


end
