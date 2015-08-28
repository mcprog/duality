local menu = {}


function menu:enter(previous)
	love.graphics.setBackgroundColor(100, 60, 255)

end

function menu:draw()
	love.graphics.print("Duality: Screen: Menu", 0, 0)
end

function menu:keypressed(key)
	if key == "left" then
		GS.switch(SPLASH)
	elseif key == "right" then
		GS.switch(CREDITS)
	end
end

return menu
