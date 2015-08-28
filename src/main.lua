GS = require "hump.gamestate"

function love.load()
	SPLASH = require "screens.splash"
	MENU = require "screens.menu"
	CREDITS = require "screens.credits"

	GS.registerEvents()
	GS.switch(SPLASH)

	
end
