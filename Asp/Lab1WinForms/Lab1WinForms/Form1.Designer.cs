namespace Lab1WinForms
{
    partial class Form1
    {
        /// <summary>
        /// Обязательная переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Требуемый метод для поддержки конструктора — не изменяйте 
        /// содержимое этого метода с помощью редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.paramY = new System.Windows.Forms.MaskedTextBox();
            this.paramX = new System.Windows.Forms.MaskedTextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.result = new System.Windows.Forms.MaskedTextBox();
            this.getResBtn = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // paramY
            // 
            this.paramY.Location = new System.Drawing.Point(213, 79);
            this.paramY.Name = "paramY";
            this.paramY.Size = new System.Drawing.Size(100, 22);
            this.paramY.TabIndex = 0;
            // 
            // paramX
            // 
            this.paramX.Location = new System.Drawing.Point(84, 80);
            this.paramX.Name = "paramX";
            this.paramX.Size = new System.Drawing.Size(100, 22);
            this.paramX.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(191, 84);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(16, 17);
            this.label1.TabIndex = 2;
            this.label1.Text = "+";
            // 
            // result
            // 
            this.result.Location = new System.Drawing.Point(397, 78);
            this.result.Name = "result";
            this.result.Size = new System.Drawing.Size(100, 22);
            this.result.TabIndex = 4;
            // 
            // getResBtn
            // 
            this.getResBtn.Location = new System.Drawing.Point(332, 78);
            this.getResBtn.Name = "getResBtn";
            this.getResBtn.Size = new System.Drawing.Size(50, 23);
            this.getResBtn.TabIndex = 5;
            this.getResBtn.Text = "=";
            this.getResBtn.UseVisualStyleBackColor = true;
            this.getResBtn.Click += new System.EventHandler(this.getResBtn_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 170);
            this.Controls.Add(this.getResBtn);
            this.Controls.Add(this.result);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.paramX);
            this.Controls.Add(this.paramY);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.MaskedTextBox paramY;
        private System.Windows.Forms.MaskedTextBox paramX;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.MaskedTextBox result;
        private System.Windows.Forms.Button getResBtn;
    }
}

