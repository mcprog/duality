local button = {
  x = 0,
  y = 0,
  width = 100,
  height = 50,
  r, g, b, a = 255, 255, 255, 255,
  shadowDiff = 5,
  buttonPressed = false
}


function button:new (x, y, width, height, r, g, b, a)
  setmetatable(button, {})
  self.x, self.y, self.width, self.height = x or self.x, y or self.y, width or self.width, height or self.height
  self.r, self.g, self.b, self.a = r or self.r, g or self.g, b or self.b, a or self.a
  return self
end

function button:draw()
  pr, pg, pb, pa = love.graphics.getColor()
  r, g, b, a = self.r, self.g, self.b, self.a
  dr, dg, db, da = r * .75, g * .75, b * .75, a
  love.graphics.setColor(dr, dg, db, da)
  love.graphics.rectangle("fill", self.x, self.y + self.shadowDiff, self.width, self.height)
  love.graphics.setColor(r, g, b, a)
  love.graphics.rectangle("fill", self.x, self.y, self.width, self.height)
  love.graphics.setColor(pr, pg, pb, pa)
end

function button:mousepressed(mX, mY)
  if (mX > self.x and mX < self.x + self.width) and (mY > self.y and mY < self.y + self.height) then
    self.y = self.y + 5
    self.buttonPressed = true
    self.shadowDiff = 0
  end
end

function button:mousereleased()
  if buttonPressed then
    self.y = self.y - 5
    self.buttonPressed = false
    self.shadowDiff = 5
  end
end

return button
