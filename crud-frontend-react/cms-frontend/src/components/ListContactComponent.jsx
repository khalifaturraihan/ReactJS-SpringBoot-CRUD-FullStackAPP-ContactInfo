import React, {useEffect, useState} from 'react'
import { deleteContact, listContact } from '../services/ContactService'
import { useNavigate } from 'react-router-dom'

const ListContactComponent = () => {

    const [contact, setContact] = useState([])

    const navigator = useNavigate();

    useEffect(() => {
        getAllContact();
    }, [])

    function getAllContact() {
        listContact().then((response) => {
            setContact(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewContact(){
        navigator('/add-contact')
    }

    function updateContact(contactId) {
        navigator(`/edit-contact/${contactId}`)
    }
    
    function removeContact(contactId) {
        console.log(contactId);

        deleteContact(contactId).then((response) =>{
            getAllContact();
        }).catch(error => {
            console.error(error);
        })
    }


  return (
    <div className='container'>
        <h2 className='text-center'>List of Contact Data</h2>
        <button className='btn btn-primary mb-2' onClick={addNewContact}>Add Contact</button>
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th className='text-center'>Contact Id</th>
                    <th className='text-center'>Contact Address</th>
                    <th className='text-center'>Contact Name</th>
                    <th className='text-center'>Contact Phone Number</th>
                    <th className='text-center'>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    contact.map(contact =>
                        <tr key={contact.contactId}>
                            <td>{contact.contactId}</td>
                            <td>{contact.contactAddress}</td>
                            <td>{contact.contactName}</td>
                            <td>{contact.contactPhoneNumber}</td>
                            <td className='text-center'>
                                <button className='btn btn-info' onClick={() => updateContact(contact.contactId)}>Update</button>
                                <span style={{ marginLeft: '8px' }}></span>
                                <button className='btn btn-danger' onClick={() => removeContact(contact.contactId)}>Delete</button>
                            </td>
                        </tr>)
                }
                <tr>

                </tr>
            </tbody>
        </table>
    </div>
  )
}

export default ListContactComponent