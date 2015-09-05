local credits = {}

function credits:enter(previous)
	love.graphics.setBackgroundColor(213, 36, 154)
	love.graphics.setColor(255, 255, 255)
end

function credits:draw()
	love.graphics.print("Duality: Screen: Credits", 0, 0)
end

function credits:keypressed(key)
	if key == "left" then
		GS.switch(MENU)
	end
end

return credits
