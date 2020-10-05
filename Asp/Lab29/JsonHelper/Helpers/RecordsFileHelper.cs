using System;
using System.IO;

namespace JsonHelper.Helpers
{
    public static class RecordsFileHelper
    {
        private const string FileName = "records.json";

        public static string GetFilePath()
        {
            return Path.Combine(AppDomain.CurrentDomain.BaseDirectory, FileName);
        }

        public static string GetRecordsContent()
        {
            string content;
            var path = GetFilePath();
            using (var streamReader = new StreamReader(path))
            {
                content = streamReader.ReadToEnd();
            }

            return content;
        }
    }
}