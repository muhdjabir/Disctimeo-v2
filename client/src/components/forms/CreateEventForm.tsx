import { useState } from 'react'
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from "@/components/ui/dialog"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Label } from "@/components/ui/label"
import { Event, EventType } from "@/types/event"
import { Club } from "@/types/club"

interface CreateEventFormProps {
    onEventCreated: (newEvent: Event) => void
    Clubs: Club[]
}

export function CreateEventForm({ onEventCreated, Clubs }: CreateEventFormProps) {
    const [isOpen, setIsOpen] = useState(false)
    const [formData, setFormData] = useState<Partial<Event>>({
        name: '',
        type: EventType.Tournament,
        description: '',
        date: new Date(),
        registrationDetails: '',
        creatorTeam: null,
        creatorUser: null // Assuming the current user is creating the event
    })

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target
        setFormData(prev => ({ ...prev, [name]: value }))
    }

    const handleSelectChange = (name: string, value: string) => {
        setFormData(prev => ({ ...prev, [name]: value }))
    }

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault()
        // In a real application, you would send this data to your API
        const newEvent: Event = {
            ...formData as Event
        }
        onEventCreated(newEvent)
        setIsOpen(false)
        setFormData({
            name: '',
            type: EventType.Tournament,
            description: '',
            date: new Date(),
            registrationDetails: '',
            creatorTeam: null,
            creatorUser: null
        })
    }

    return (
        <Dialog open={isOpen} onOpenChange={setIsOpen}>
            <DialogTrigger asChild>
                <Button>Create New Event</Button>
            </DialogTrigger>
            <DialogContent className="sm:max-w-[425px]">
                <DialogHeader>
                    <DialogTitle>Create New Event</DialogTitle>
                </DialogHeader>
                <form onSubmit={handleSubmit} className="space-y-4">
                    <div className="space-y-2">
                        <Label htmlFor="name">Event Name</Label>
                        <Input
                            id="name"
                            name="name"
                            value={formData.name}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className="space-y-2">
                        <Label htmlFor="type">Event Type</Label>
                        <Select
                            name="type"
                            value={formData.type}
                            onValueChange={(value) => handleSelectChange('type', value)}
                        >
                            <SelectTrigger>
                                <SelectValue placeholder="Select event type" />
                            </SelectTrigger>
                            <SelectContent>
                                {Object.values(EventType).map((type) => (
                                    <SelectItem key={type} value={type}>{type}</SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                    </div>
                    <div className="space-y-2">
                        <Label htmlFor="description">Description</Label>
                        <Textarea
                            id="description"
                            name="description"
                            value={formData.description}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className="space-y-2">
                        <Label htmlFor="date">Event Date</Label>
                        <Input
                            id="date"
                            name="date"
                            type="date"
                            value={formData.date ? new Date(formData.date).toISOString().split('T')[0] : ''}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className="space-y-2">
                        <Label htmlFor="registrationDetails">Registration Details</Label>
                        <Input
                            id="registrationDetails"
                            name="registrationDetails"
                            value={formData.registrationDetails}
                            onChange={handleInputChange}
                            required
                        />
                    </div>
                    <div className="space-y-2">
                        <Label htmlFor="creatorTeam">Creator Club</Label>
                        <Select
                            name="creatorTeam"
                            value={formData.creatorTeam?.id || ''}
                            onValueChange={(value) => handleSelectChange('creatorTeam', value)}
                        >
                            <SelectTrigger>
                                <SelectValue placeholder="Select creator Club" />
                            </SelectTrigger>
                            <SelectContent>
                                <SelectItem value="No Club">No Club</SelectItem>
                                {Clubs.map((Club) => (
                                    <SelectItem key={Club.id} value={Club.id}>{Club.name}</SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                    </div>
                    <div className='flex justify-end'>
                        <Button type="submit">Create Event</Button>
                    </div>
                </form>
            </DialogContent>
        </Dialog>
    )
}
