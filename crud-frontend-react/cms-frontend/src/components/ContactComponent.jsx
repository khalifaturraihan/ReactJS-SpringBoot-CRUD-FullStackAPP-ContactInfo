import React, { useEffect, useState } from 'react'
import { createContact, getContact, updateContact } from '../services/ContactService'
import { useNavigate, useParams } from 'react-router-dom'

const ContactComponent = () => {

    const [contactAddress, setContactAddress] = useState('')
    const [contactName, setContactName] = useState('')
    const [contactPhoneNumber, setContactPhoneNumber] = useState('')

    const {contactId} = useParams();

    const [errors, setErrors] = useState({
        contactName: '',
        contactAddress: '',
        contactPhoneNumber: ''
    })

    const navigator = useNavigate();

    useEffect(() => {
        if(contactId){
            getContact(contactId).then((response) => {
                setContactName(response.data.contactName);
                setContactAddress(response.data.contactAddress);
                setContactPhoneNumber(response.data.contactPhoneNumber);
            }).catch(error => {
                console.error(error);
            })
        }
    }, [contactId])

    function saveOrUpdateContact(e){
       e.preventDefault();

       if(validateForm()){

            const contact = {contactName, contactAddress, contactPhoneNumber}
            console.log(contact) 

            if(contactId){
                updateContact(contactId, contact).then((response) => {
                    console.log(response.data);
                    navigator('/contact');
                }).catch(error => {
                    console.error(error);
                })
            } else {
                createContact(contact).then((response) => {
                    console.log(response.data);
                    navigator('/contact')
                    }).catch(error => {
                        console.error(error);
                    })
            }
       }
    }

    function validateForm(){
        let valid = true;

        const errorsCopy = {... errors}

        if(contactName.trim()){
            errorsCopy.contactName = '';
        } else {
            errorsCopy.contactName = 'Contact Name is required';
            valid = false;
        }

        if(contactAddress.trim()){
            errorsCopy.contactAddress = '';
        } else {
            errorsCopy.contactAddress = 'Contact Address is required';
            valid = false;
        }

        if(contactPhoneNumber.trim()){
            errorsCopy.contactPhoneNumber = '';
        } else {
            errorsCopy.contactPhoneNumber = 'Phone Number is required';
            valid = false;
        }

        setErrors(errorsCopy);

        return valid;
    }

    function pageTitle(){
        if(contactId){
            return <h2 className='text-center'>Update Contact</h2>
        }else{
            return <h2 className='text-center'>Add Contact</h2>
        }
    }

  return (
    <div className='container'>
        <br /> <br />
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {
                    pageTitle()
                }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Contact Name:</label>
                            <input
                                type='text'
                                placeholder='Enter Contact Name'
                                name='contactName'
                                value={contactName}
                                className={`form-control ${ errors.contactName ? 'is-invalid': ''}`}
                                onChange={(e) => setContactName(e.target.value)}
                            ></input>
                            { errors.contactName && <div className='invalid-feedback'> { errors.contactName }</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Contact Address:</label>
                            <input
                                type='text'
                                placeholder='Enter Contact Address'
                                name='contactAddress'
                                value={contactAddress}
                                className={`form-control ${ errors.contactAddress? 'is-invalid': ''}`}
                                onChange={(e) => setContactAddress(e.target.value)}
                            ></input>
                            { errors.contactAddress && <div className='invalid-feedback'> { errors.contactAddress }</div>}
                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Contact Phone Number:</label>
                            <input
                                type='text'
                                placeholder='Enter Contact Phone Number'
                                name='contactPhoneNumber'
                                value={contactPhoneNumber}
                                className={`form-control ${ errors.contactPhoneNumber? 'is-invalid': ''}`}
                                onChange={(e) => setContactPhoneNumber(e.target.value)}
                            ></input>
                            { errors.contactPhoneNumber && <div className='invalid-feedback'> { errors.contactPhoneNumber }</div>}
                        </div>

                        <button className='btn btn-success' onClick={saveOrUpdateContact}>Submit</button>
                    </form>
                </div>

            </div>
        </div>

    </div>
  )
}

export default ContactComponent