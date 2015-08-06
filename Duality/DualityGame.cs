using System;
using System.Collections;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

namespace Duality
{
    /// <summary>
    /// This is the main type for your game.
    /// </summary>
    public class DualityGame : Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        Vector2 position;
        Texture2D texture;
        Song whoDatSong;
        private AnimatedSprite animatedSprite;
        private TextureIndexed stoneTile;
        private TextureAtlas stoneTileAtas;
        //private ArrayList tilesArray;
        private Grid grid;
        private bool justRightPressed;
        private bool justRightReleased;
        private Color flushColor;
        

        public DualityGame()
        {
            this.Window.Title = "Duality = Launching";
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
            //TargetElapsedTime = TimeSpan.FromSeconds(1f / 60f);
            this.IsFixedTimeStep = false;
            //this.TargetElapsedTime = new TimeSpan(0, 0, 0, 0, 16);
            this.IsMouseVisible = true;
            graphics.SynchronizeWithVerticalRetrace = false;
            
        }

        protected override void OnActivated(object sender, EventArgs args)
        {
            Window.Title = "Duality = Activated";
           
            
            MediaPlayer.Resume();
            base.OnActivated(sender, args);
        }

        protected override void OnDeactivated(object sender, EventArgs args)
        {
            Window.Title = "Duality != Activated";
            MediaPlayer.Pause();
            base.OnDeactivated(sender, args);
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            
            base.Initialize();
            position = new Vector2(graphics.GraphicsDevice.Viewport.Width / 2f,
                                    graphics.GraphicsDevice.Viewport.Height / 2f);
            grid = new Grid(GraphicsDevice.Viewport.Width, GraphicsDevice.Viewport.Height);
            flushColor = Color.White;


        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            // TODO: use this.Content to load your game content here
            texture = this.Content.Load<Texture2D>("portal");
            animatedSprite = new AnimatedSprite(texture, 2, 8, 0.1);
            stoneTileAtas = new TextureAtlas(this.Content.Load<Texture2D>("stone-tiles1"), 2, 8);
            stoneTile = new TextureIndexed(stoneTileAtas);
            whoDatSong = this.Content.Load<Song>("J Cole - Who Dat");
            //MediaPlayer.Play(whoDatSong);
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// game-specific content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
            
            
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            if (IsActive)
            {
                KeyboardState kState = Keyboard.GetState();
                MouseState mState = Mouse.GetState();

                if (kState.IsKeyDown(Keys.Escape))
                    Exit();
                if (kState.IsKeyDown(Keys.Delete))
                {
                    grid.ClearGrid(); 
                }
                stoneTile.Location.X = ((int)(mState.X / 16f)) * 16f + 8;
                stoneTile.Location.Y = ((int)(mState.Y / 16f)) * 16f + 8;

                if (mState.LeftButton == ButtonState.Pressed)
                {
                    grid.Add(new TextureIndexed(stoneTile.Atlas, stoneTile.Location));
                }
                /*if (mState.RightButton == ButtonState.Pressed & !justRightReleased)
                {
                    justRightPressed = true;
                    
                }
                if (mState.RightButton == ButtonState.Released & justRightPressed)
                {
                    justRightReleased = true;
                }
                if (justRightPressed & justRightReleased)
                {
                    ++stoneTile.Atlas.Index;
                    if (stoneTile.Atlas.Index > stoneTile.Atlas.TotalFrames)
                        stoneTile.Atlas.Index = 0;
                    justRightPressed = false;
                    justRightReleased = false;
                }*/
                if (mState.RightButton == ButtonState.Pressed)
                {
                    grid.RemoveAt((int)(stoneTile.Location.X / 16), (int)(stoneTile.Location.Y / 16));
                }
                if (mState.MiddleButton == ButtonState.Pressed)
                {
                    grid.ClearGrid();
                }
                flushColor.R = (byte)(mState.ScrollWheelValue / 64);
                flushColor.G = (byte)(mState.ScrollWheelValue / 64);
                flushColor.B = (byte)(mState.ScrollWheelValue / 64);
                
                
                

                grid.Update();

            }
            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(flushColor);

            // TODO: Add your drawing code here

            //animatedSprite.Draw(spriteBatch, position);
            grid.Draw(spriteBatch, Color.White);
            stoneTile.Draw(spriteBatch, new Color(Color.White, .75f));


            base.Draw(gameTime);
        }
    }
}
