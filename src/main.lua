local x = love.window.getWidth()/2
local y = love.window.getHeight()/2
local intext = ""

function love.load()
	love.physics.setMeter(32)
	world = love.physics.newWorld(0, 9.81*32, true)
	playerImage = love.graphics.newImage("assets/textures/wizard.png")

	objects = {}

	objects.ground = {}
	objects.ground.body = love.physics.newBody(world, love.window.getWidth()/2, love.window.getHeight()-50/2)
	objects.ground.shape = love.physics.newRectangleShape(love.window.getWidth(), 50)
	objects.ground.fixture = love.physics.newFixture(objects.ground.body, objects.ground.shape)

	objects.ball = {}
	objects.ball.body = love.physics.newBody(world, x, y, "dynamic")
	objects.ball.shape = love.physics.newCircleShape(12)
	objects.ball.fixture = love.physics.newFixture(objects.ball.body, objects.ball.shape, 1)
end


function love.draw()
	love.graphics.setColor(72, 160, 14)
	love.graphics.polygon("fill", objects.ground.body:getWorldPoints(objects.ground.shape:getPoints()))

	love.graphics.setColor(193, 47, 0)
	love.graphics.circle("fill", objects.ball.body:getX(), objects.ball.body:getY(), objects.ball.shape:getRadius())


	love.graphics.setColor(255, 255, 255)
	love.graphics.print("Type to begin:", 400, 315)
	love.graphics.print(intext .. " |", 400, 330)
	love.graphics.draw(playerImage, x, y)


end

function love.update(dt)
world:update(dt)

	if love.keyboard.isDown("d") then
		x = x + 500 * dt
	end

	if love.keyboard.isDown("up") then --press the left arrow key to push the ball to the left
		objects.ball.body:applyForce(0, -200)
	end
	if love.keyboard.isDown("right") then --press the right arrow key to push the ball to the right
    	objects.ball.body:applyForce(400, 0)
  	elseif love.keyboard.isDown("left") then --press the left arrow key to push the ball to the left
    	objects.ball.body:applyForce(-400, 0)
  	elseif love.keyboard.isDown("return") then --press the up arrow key to set the ball in the air
    	objects.ball.body:setPosition(love.window.getWidth()/2, love.window.getHeight()/2)
    	objects.ball.body:setLinearVelocity(0, 0) --we must set the velocity to zero to prevent a potentially large velocity generated by the change in position
  	end
end

function love.textinput( text )
	intext = intext .. text
end

function love.resize(w, h)
	x = w/2
	y = h/2
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
