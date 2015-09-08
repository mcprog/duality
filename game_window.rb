#require 'rubygems'
#require 'gosu'
#require_relative 'splash'

WIDTH, HEIGHT = 960, 640

class GameWindow < Gosu::Window

  attr_accessor :state

  def initialize
    super WIDTH, HEIGHT, false
    self.caption = "Duality pre-Alpha!"
    #@splash = Splash.new


  end

  def needs_cursor?
    return true
  end

  def update
    dt = self.update_interval
    self.caption = "Duality pre-Alpha! " + Gosu.fps.to_s + " FPS, " + dt.to_s + " ms dt"
    #@splash.update(dt)
    @state.update(dt)
  end

  def draw
    #@splash.draw
    @state.draw
  end

  def needs_redraw?
    @state.needs_redraw?
  end

end

#window = GameWindow.new
#window.show
