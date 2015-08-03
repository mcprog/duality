using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Duality
{
    public class AnimatedSprite : ICanBeDrawn
    {
        public TextureAtlas Atlas;
        public double FrameTime { get; set; }
        //private int currentFrame;
        private int totalFrames;
        private double cumIntTime;

        public AnimatedSprite(Texture2D texture, int rows, int columns)
        {
            Atlas.Texture = texture;
            Atlas.Rows = rows;
            Atlas.Columns = columns;
            Atlas.Index = 0;
            FrameTime = 1;
            totalFrames = Atlas.Rows*Atlas.Columns;
        }

        public AnimatedSprite(Texture2D texture, int rows, int columns, double frameTime)
        {
            Atlas.Texture = texture;
            Atlas.Rows = rows;
            Atlas.Columns = columns;
            Atlas.Index = 0;
            FrameTime = frameTime;
            totalFrames = Atlas.Rows * Atlas.Columns;
        }

        public void Update(GameTime gameTime)
        {
            cumIntTime += gameTime.ElapsedGameTime.TotalSeconds;
            if (cumIntTime > FrameTime)
            {
                ++Atlas.Index;
                cumIntTime = 0;
            }
            if (Atlas.Index == totalFrames)
                Atlas.Index = 0;
        }

        public void Draw(SpriteBatch spriteBatch, Vector2 location)
        {
            int width = Atlas.Texture.Width / Atlas.Columns;
            int height = Atlas.Texture.Height / Atlas.Rows;
            int row = (int)((float)Atlas.Index / (float)Atlas.Columns);
            int column = Atlas.Index % Atlas.Columns;

            Rectangle sourceRectangle = new Rectangle(width * column, height * row, width, height);
            Rectangle destinationRectangle = new Rectangle((int)(location.X - width / 2f), (int)(location.Y - height / 2f), width, height);

            spriteBatch.Begin();
            spriteBatch.Draw(Atlas.Texture, destinationRectangle, sourceRectangle, Color.White);
            spriteBatch.End();
        }
    }
}