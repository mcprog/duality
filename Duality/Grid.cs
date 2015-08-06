using System;
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
            if (0 <= row && row < Map.GetLength(0) && 0 <= column && column < Map.GetLength(1))
            {
                Map[row, column] = textureIndexed;
            }
            
        }

        public void Add(TextureIndexed textureIndexed)
        {
            Add(textureIndexed, (int)(textureIndexed.Location.X / 16), (int)(textureIndexed.Location.Y / 16));
        }

        public void RemoveAt(int row, int column)
        {
            Map[row, column] = null;
        }

        public void Update()
        {
            
            for (int r = 0; r < Map.GetLength(0); ++r)
            {
                for (int c = 0; c < Map.GetLength(1); ++c)
                {
                    if (Map[r, c] != null)
                    {
                        string tileDeterminer = GetTouchingUp(r, c) + GetTouchingDown(r, c) + 
                            GetTouchingLeft(r, c) + GetTouchingRight(r, c);
                        //part 1: set a score value
                        switch (tileDeterminer)
                        {
                            /*case "UpDownLeftRight":
                                Map[r, c].Atlas.Index = 0;
                                break;
                            case "UpDownLeft":
                                Map[r, c].Atlas.Index = 1;
                                break;
                            case "UpDown":
                                Map[r, c].Atlas.Index = 14;
                                break;*/
                            case "Up":
                                Map[r, c].Atlas.Index = 11;
                                break;
                            case "Down":
                                Map[r, c].Atlas.Index = 12;
                                break;
                            case "Left":
                                Map[r, c].Atlas.Index = 10;
                                break;
                            case "Right":
                                Map[r, c].Atlas.Index = 9;
                                break;
                                // points
                            case "UpDown":
                                Map[r, c].Atlas.Index = 14;
                                break;
                            case "LeftRight":
                                Map[r, c].Atlas.Index = 13;
                                break;
                            case "UpLeft":
                                Map[r, c].Atlas.Index = 6;
                                // mirrors
                                break;
                            case "UpRight":
                                Map[r, c].Atlas.Index = 5;
                                break;
                            case "DownLeft":
                                Map[r, c].Atlas.Index = 8;
                                break;
                            case "DownRight":
                                Map[r, c].Atlas.Index = 7;
                                break;
                                //corners
                            case "UpDownLeft":
                                Map[r, c].Atlas.Index = 2;
                                break;
                            case "UpDownRight":
                                Map[r, c].Atlas.Index = 1;
                                break;
                            case "UpLeftRight":
                                Map[r, c].Atlas.Index = 3;
                                break;
                            case "DownLeftRight":
                                Map[r, c].Atlas.Index = 4;
                                break;
                                //edges
                            case "UpDownLeftRight":
                                Map[r, c].Atlas.Index = 0;
                                break;
                            default:
                                Map[r, c].Atlas.Index = 15;
                                break;
                        }
                        

                    }/*
                    //part 1: set a score value
                    if (isTouchingUp(r, c))
                    {
                        //Map[r, c].Atlas.Index = 15;
                        score += 1000;
                    }
                    if (isTouchingDown(r, c))
                    {
                        score += 100;
                    }
                    if (isTouchingLeft(r, c))
                    {
                        score += 10;
                    }
                    if (isTouchingRight(r, c))
                    {
                        ++score;
                    }

                    //part 2: eval. score
                    switch (score)
                    {
                        case 0:
                            Map[r, c].Atlas.Index = 0;
                            break;
                        default:
                            Map[r, c].Atlas.Index = 15;
                            break;

                    }*/

                    //if (r % 2 == 0)
                    //{
                        
                    //}

                }
            }
        }

        protected string GetTouchingUp(int row, int column)
        {
            if (column < Map.GetLength(1) - 1)
            {
                return Map[row, column + 1] != null ? "Up" : "";
            }
            return "";
        }
        protected string GetTouchingDown(int row, int column)
        {
            if (column > 0)
            {
                return Map[row, column - 1] != null ? "Down" : "";
            }
            return "";
        }
        protected string GetTouchingLeft(int row, int column)
        {
            if (row > 0)
            {
                return Map[row - 1, column] != null ? "Left" : "";
            }
            return "";

        }
        protected string GetTouchingRight(int row, int column)
        {
            if (row < Map.GetLength(0) - 1)
            {
                return Map[row + 1, column] != null ? "Right" : "";
            }
            return "";
        }

        public void ClearGrid()
        {
            Map = new TextureIndexed[Width/16,Height/16];
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