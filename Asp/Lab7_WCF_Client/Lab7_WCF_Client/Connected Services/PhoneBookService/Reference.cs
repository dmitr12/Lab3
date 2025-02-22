﻿//------------------------------------------------------------------------------
// <auto-generated>
//     Этот код создан программой.
//     Исполняемая версия:4.0.30319.42000
//
//     Изменения в этом файле могут привести к неправильной работе и будут потеряны в случае
//     повторной генерации кода.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Lab7_WCF_Client.PhoneBookService {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="TS", Namespace="http://schemas.datacontract.org/2004/07/Lab7_WCF_Server")]
    [System.SerializableAttribute()]
    public partial class TS : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int IdField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string PhoneField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string SurnameField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int Id {
            get {
                return this.IdField;
            }
            set {
                if ((this.IdField.Equals(value) != true)) {
                    this.IdField = value;
                    this.RaisePropertyChanged("Id");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Phone {
            get {
                return this.PhoneField;
            }
            set {
                if ((object.ReferenceEquals(this.PhoneField, value) != true)) {
                    this.PhoneField = value;
                    this.RaisePropertyChanged("Phone");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string Surname {
            get {
                return this.SurnameField;
            }
            set {
                if ((object.ReferenceEquals(this.SurnameField, value) != true)) {
                    this.SurnameField = value;
                    this.RaisePropertyChanged("Surname");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="PhoneBookService.IPhoneBook")]
    public interface IPhoneBook {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/GetAllTS", ReplyAction="http://tempuri.org/IPhoneBook/GetAllTSResponse")]
        Lab7_WCF_Client.PhoneBookService.TS[] GetAllTS();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/GetAllTS", ReplyAction="http://tempuri.org/IPhoneBook/GetAllTSResponse")]
        System.Threading.Tasks.Task<Lab7_WCF_Client.PhoneBookService.TS[]> GetAllTSAsync();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/AddTS", ReplyAction="http://tempuri.org/IPhoneBook/AddTSResponse")]
        string AddTS(Lab7_WCF_Client.PhoneBookService.TS ts);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/AddTS", ReplyAction="http://tempuri.org/IPhoneBook/AddTSResponse")]
        System.Threading.Tasks.Task<string> AddTSAsync(Lab7_WCF_Client.PhoneBookService.TS ts);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/EditTS", ReplyAction="http://tempuri.org/IPhoneBook/EditTSResponse")]
        string EditTS(Lab7_WCF_Client.PhoneBookService.TS ts);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/EditTS", ReplyAction="http://tempuri.org/IPhoneBook/EditTSResponse")]
        System.Threading.Tasks.Task<string> EditTSAsync(Lab7_WCF_Client.PhoneBookService.TS ts);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/DeleteTS", ReplyAction="http://tempuri.org/IPhoneBook/DeleteTSResponse")]
        void DeleteTS(int id);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IPhoneBook/DeleteTS", ReplyAction="http://tempuri.org/IPhoneBook/DeleteTSResponse")]
        System.Threading.Tasks.Task DeleteTSAsync(int id);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface IPhoneBookChannel : Lab7_WCF_Client.PhoneBookService.IPhoneBook, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class PhoneBookClient : System.ServiceModel.ClientBase<Lab7_WCF_Client.PhoneBookService.IPhoneBook>, Lab7_WCF_Client.PhoneBookService.IPhoneBook {
        
        public PhoneBookClient() {
        }
        
        public PhoneBookClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public PhoneBookClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public PhoneBookClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public PhoneBookClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public Lab7_WCF_Client.PhoneBookService.TS[] GetAllTS() {
            return base.Channel.GetAllTS();
        }
        
        public System.Threading.Tasks.Task<Lab7_WCF_Client.PhoneBookService.TS[]> GetAllTSAsync() {
            return base.Channel.GetAllTSAsync();
        }
        
        public string AddTS(Lab7_WCF_Client.PhoneBookService.TS ts) {
            return base.Channel.AddTS(ts);
        }
        
        public System.Threading.Tasks.Task<string> AddTSAsync(Lab7_WCF_Client.PhoneBookService.TS ts) {
            return base.Channel.AddTSAsync(ts);
        }
        
        public string EditTS(Lab7_WCF_Client.PhoneBookService.TS ts) {
            return base.Channel.EditTS(ts);
        }
        
        public System.Threading.Tasks.Task<string> EditTSAsync(Lab7_WCF_Client.PhoneBookService.TS ts) {
            return base.Channel.EditTSAsync(ts);
        }
        
        public void DeleteTS(int id) {
            base.Channel.DeleteTS(id);
        }
        
        public System.Threading.Tasks.Task DeleteTSAsync(int id) {
            return base.Channel.DeleteTSAsync(id);
        }
    }
}
