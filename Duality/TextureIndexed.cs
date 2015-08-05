using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Duality
{
    public class TextureIndexed : ICanBeDrawn
    {
        public TextureAtlas Atlas;
        public Vector2 Location;

        public TextureIndexed(Texture2D texture, int rows, int columns, int index)
        {
            Atlas.Texture = texture;
            Atlas.Rows = rows;
            Atlas.Columns = columns;
            Atlas.Index = index;
            Location = Vector2.Zero;
        }

        public TextureIndexed(Texture2D texture, int rows, int columns, int index, Vector2 location)
        {
            Atlas.Texture = texture;
            Atlas.Rows = rows;
            Atlas.Columns = columns;
            Atlas.Index = index;
            Location = location;
        }

        public TextureIndexed(TextureAtlas atlas)
        {
            Atlas = atlas;
            Location = Vector2.Zero;
        }

        public TextureIndexed(TextureAtlas atlas, Vector2 location)
        {
            Atlas = atlas;
            Location = location;
        }

        public void Draw(SpriteBatch spriteBatch, Color color)
        {
            int width = Atlas.Texture.Width / Atlas.Columns;
            int height = Atlas.Texture.Height / Atlas.Rows;
            int row = (int)((float)Atlas.Index / (float)Atlas.Columns);  
            int column = Atlas.Index % Atlas.Columns;

            Rectangle sourceRectangle = new Rectangle(width * column, height * row, width, height);
            Rectangle destinationRectangle = new Rectangle((int)(Location.X - width / 2f), (int)(Location.Y - height / 2f), width, height);

            spriteBatch.Begin();
            spriteBatch.Draw(Atlas.Texture, destinationRectangle, sourceRectangle, color);
            spriteBatch.End();
        }
    }
}