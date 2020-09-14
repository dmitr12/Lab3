using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab1WinForms
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
            result.Enabled = false;
        }

        private void getResBtn_Click(object sender, EventArgs e)
        {
            if (paramX.Text.Length == 0 || paramY.Text.Length == 0)
                MessageBox.Show("Заполните поле/поля для ввода");
            else
            {
                int x, y;
                if (!int.TryParse(paramX.Text, out x) || !int.TryParse(paramY.Text, out y))
                    MessageBox.Show("Некорректные поле/поля для ввода");
                else
                {
                    try
                    {
                        WebRequest req = WebRequest.Create("http://localhost:49685/task4");
                        req.Method = "POST";
                        req.Timeout = 10000;
                        req.ContentType = "application/x-www-form-urlencoded";
                        byte[] bts = Encoding.UTF8.GetBytes("x=" + paramX.Text + "&" + "y=" + paramY.Text);
                        req.ContentLength = bts.Length;
                        Stream stream = req.GetRequestStream();
                        stream.Write(bts, 0, bts.Length);
                        stream.Close();
                        WebResponse resp = req.GetResponse();
                        Stream recStream = resp.GetResponseStream();
                        StreamReader streamReader = new StreamReader(recStream, Encoding.UTF8);
                        char[] read = new char[256];
                        int count = streamReader.Read(read, 0, 256);
                        string strOut = "";
                        while (count > 0)
                        {
                            string str = new string(read, 0, count);
                            strOut += str;
                            count = streamReader.Read(read, 0, 256);
                        }
                        result.Text = strOut;
                    }
                    catch (Exception ex)
                    {
                        MessageBox.Show("Ошибка: " + ex.Message);
                    }
                }
            }
        }
    }
}
