local button = {
  x = 0,
  y = 0,
  width = 100,
  height = 50,
  color = {255, 255, 255, 255}
}

function button:new (x, y, width, height, color)
  setmetatable(button, {})
  self.x, self.y, self.width, self.height = x or self.x, y or self.y, width or self.width, height or self.height
  self.color = color or self.color
  return self
end

function button:draw()
  r, g, b, a = love.graphics.getColor()
  love.graphics.setColor(self.color)
  love.graphics.rectangle("fill", self.x, self.y, self.width, self.height)
  love.graphics.setColor(r, b, g, a)
end

function button:mousepressed(mX, mY)
  if (mX > self.x and mX < self.x + self.width) and (mY > self.y and mY < self.y + self.height) then
    self.x = self.x + 20
  end
end

return button
