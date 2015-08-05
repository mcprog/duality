using System.Collections.Generic;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace Duality
{
    public class Grid : ICanBeDrawn
    {
        public TextureIndexed[,] Map;
        private int Width;
        private int Height;

        public Grid(int width, int height)
        {
            Width = width;
            Height = height;
            Map = new TextureIndexed[Width/16, Height/16];
        }

        public void Add(TextureIndexed textureIndexed, int row, int column)
        {
            Map[row, column] = textureIndexed;
        }

        public void Add(TextureIndexed textureIndexed)
        {
            Add(textureIndexed, (int)(textureIndexed.Location.X / 16), (int)(textureIndexed.Location.Y / 16));
        }

        public void RemoveAt(int row, int column)
        {
            Map[row, column] = null;
        }

        public void Draw(SpriteBatch spriteBatch, Color color)
        {
            foreach (TextureIndexed tile in Map)
            {
                
                //null propgation operator is SO COOL!    
                tile?.Draw(spriteBatch, color);
                
            }
        }
    }
}