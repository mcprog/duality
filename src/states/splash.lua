local splash = {}

local timer = 0

function splash:enter(previous)
	love.graphics.setBackgroundColor(0, 50, 100)
	timer = 0
end

function splash:draw()
	love.graphics.print("Duality: Screen: Splash", 0, 0)
end

function splash:update()
	
end

function splash:keypressed(key)
	if key == "right" then
		GS.switch(MENU)
	end
end

return splash