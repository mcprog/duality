local x = 0
local intext = ""

function love.load()
	playerImage = love.graphics.newImage("assets/textures/wizard.png")
end


function love.draw()
	
	love.graphics.print("Type to begin:", 400, 315)
	love.graphics.print(intext .. " |", 400, 330)
	love.graphics.draw(playerImage, x, 0)
end

function love.update(dt)
	if love.keyboard.isDown("d") then
		x = x + 500 * dt
	end
end

function love.textinput( text )
	intext = intext .. text
end

function love.resize(w, h)
	
end

function love.keypressed( key )
	if key == "escape" then
		love.event.quit()
	elseif key == "i" then
		local screenshot = love.graphics.newScreenshot()
		screenshot:encode('duality' .. os.time() .. '.png')
		love.window.setTitle("Screenshot taken")
	elseif key == "o" then
		love.system.openURL("file://" .. love.filesystem.getSaveDirectory())
	end
end

function love.keyreleased( key )
	if key == "i" then
		love.window.setTitle("Duality pre-Alpha mess")
	end
end
