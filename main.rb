require 'rubygems'
require 'gosu'
require_relative 'splash'

WIDTH, HEIGHT = 960, 640

class MyWindow < Gosu::Window

  splash = nil
  def initialize
    super WIDTH, HEIGHT, false
    self.caption = "Duality pre-Alpha!"
    @splash = Splash.new


  end

  def needs_cursor?
    return true
  end

  def update
    dt = self.update_interval
    self.caption = "Duality pre-Alpha! " + Gosu.fps.to_s + " FPS, " + dt.to_s + " ms dt"
    @splash.update(dt)
  end

  def draw
    @splash.draw
    #if @splash_timer <= 3000
    #  @splash_text_img.draw(WIDTH / 2 - @splash_text_img.width / 2, HEIGHT / 2 - @splash_text_img.height / 2, 0)
    #end

    # Gosu.draw_rect(0, 0, 100, 60, Gosu::Color.new(255, 255, 0, 0))
  end
end

window = MyWindow.new
window.show
