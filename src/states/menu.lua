button = require "ui/button"

local menu = {}

local button1 = button:new(50, 50, 150, 60, {0, 255, 255, 255})

function menu:enter(previous)
	love.graphics.setBackgroundColor(100, 60, 255)
	love.graphics.setColor(255, 255, 255)

end

function menu:draw()
	love.graphics.print("Duality: Screen: Menu", 0, 0)
	button1:draw()
end

function menu:keypressed(key)
	if key == "left" then
		GS.switch(SPLASH)
	elseif key == "right" then
		GS.switch(CREDITS)
	end
end


function menu:mousepressed(x, y, button)
	button1:mousepressed(x, y)
end

return menu
