using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab7_WCF_Client
{
    public partial class Form1 : Form
    {
        PhoneBookService.PhoneBookClient client = new PhoneBookService.PhoneBookClient("BasicHttpBinding_IPhoneBook");
        PhoneBookService.TS elementTS = new PhoneBookService.TS();
        DataTable table = new DataTable();

        public Form1()
        {
            InitializeComponent();
            ListTS.ReadOnly = true;
            btnEdit.Enabled = false;
        }

        void ReloadTable()
        {
            ListTS.Rows.Clear();
            List<PhoneBookService.TS> list = client.GetAllTS().ToList().OrderBy(item => item.Surname).ToList();
            foreach (PhoneBookService.TS ts in list)
            {
                ListTS.Rows.Add(ts.Id, ts.Surname, ts.Phone, "Изменить", "Удалить");
            }
        }

        private void Form1_FormClosed(object sender, FormClosedEventArgs e)
        {
            client.Close();
        }

        private void btnAdd_Click_1(object sender, EventArgs e)
        {

            if (surnameTextBox.Text == "" || phoneTextBox.Text == "")
            {
                MessageBox.Show("Форма 'Добавление': Необходимо заполнить все поля");
            }
            else if (!Regex.IsMatch(phoneTextBox.Text, @"^[1-9]{1}[0-9]{6}$"))
            {
                MessageBox.Show("Необходимо ввести 7 цифр, начиная не с 0");
            }
            else
            {
                string status = client.AddTS(new PhoneBookService.TS { Surname = surnameTextBox.Text, Phone = phoneTextBox.Text });
                if (status != "ok")
                    MessageBox.Show(status);
                else
                {
                    phoneTextBox.Text = surnameTextBox.Text = "";
                    ReloadTable();
                }
            }
        }

        private void btnEdit_Click(object sender, EventArgs e)
        {
            if (surnameEditTextBox.Text == "" || phoneEditTextBox.Text == "")
            {
                MessageBox.Show("Форма 'Изменение': Необходимо заполнить все поля");
            }
            else if (!Regex.IsMatch(phoneEditTextBox.Text, @"^[1-9]{1}[0-9]{6}$"))
            {
                MessageBox.Show("Необходимо ввести 7 цифр, начиная не с 0");
            }
            else
            {
                elementTS.Surname = surnameEditTextBox.Text;
                elementTS.Phone = phoneEditTextBox.Text;
                string status = client.EditTS(elementTS);
                if (status != "ok")
                    MessageBox.Show(status);
                else
                {
                    phoneEditTextBox.Text = surnameEditTextBox.Text = "";
                    ReloadTable();
                    btnEdit.Enabled = false;
                }
            }
        }

        private void ListTS_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            switch (ListTS[e.ColumnIndex, e.RowIndex].Value)
            {
                case "Изменить":
                    elementTS = new PhoneBookService.TS
                    {
                        Id = int.Parse(ListTS.Rows[e.RowIndex].Cells["Id"].Value.ToString()),
                        Surname = ListTS.Rows[e.RowIndex].Cells["Surname"].Value.ToString(),
                        Phone = ListTS.Rows[e.RowIndex].Cells["Phone"].Value.ToString()
                    };
                    surnameEditTextBox.Text = ListTS.Rows[e.RowIndex].Cells["Surname"].Value.ToString();
                    phoneEditTextBox.Text = ListTS.Rows[e.RowIndex].Cells["Phone"].Value.ToString();
                    btnEdit.Enabled = true;
                    break;
                case "Удалить":
                    client.DeleteTS(int.Parse(ListTS.Rows[e.RowIndex].Cells["Id"].Value.ToString()));
                    ReloadTable();
                    break;
            }
        }

        private void Form1_Load_1(object sender, EventArgs e)
        {
            ReloadTable();
        }
    }
}
